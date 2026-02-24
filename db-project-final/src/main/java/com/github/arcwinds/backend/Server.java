package com.github.arcwinds.backend;

import com.github.arcwinds.common.Customer;
import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.Reservation;
import com.github.arcwinds.common.Ticket;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {

    Database db;

    public Server() {
        try {
            db = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Database getDb() {
        return this.db;
    }

    public Customer loginUser(String email, String password) {
        return db.getCustomersTable().authenticateCustomer(email, password);
    }

    public boolean loginAdmin(String username, String password) {
        if (username.equals("admin") && password.equals("87654321")) {
            return true;
        }
        return false;
    }

    //epeidi to parathuro den kserei gia tin basi dedomenwn poy einai ston server
    public ArrayList<Event> getAllEvents() {
        try {
            return db.getEventsTable().getAllEvents(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Δημιουργία πελάτη
     */
    public boolean addCustomer(UUID customer_id, String full_name, String email, String password) {
        return db.getCustomersTable().insertCustomer(customer_id, full_name, email, password);
    }

    /*
     * Δημιουργία event και tickets (στο tickets table)
     * normalTickets αρχικά κανονικά εισιτήρια
     * normalPrice τιμή που θα έχουν τα αρχικά κανονικά εισιτήρια
     * vipTickets αρχικά vip εισιτήρια
     * vipPrice τιμή που θα έχουν τα αρχικά vip εισιτήρια
     */
    public boolean addEvent(String name, String date, String time, String eventType, int capacity,
            int vipTickets, int normalPrice, int vipPrice) {
        return db.getEventsTable().insertEvent(
                UUID.randomUUID(), name, date, time, eventType, capacity,
                vipTickets, normalPrice, vipPrice
        );
    }

    /*
     * Επιστρέφει πόσες διαθέσιμες θέσεις υπάρχουν για ένα event
     */
    public int getAvailableTicketAmount(UUID eventID, boolean isVip) {
        return db.getTicketsTable().getAvailableTicketAmount(eventID, isVip);
    }

    /*
     * Κάνει κράτηση, αφαιρεί χρήματα απο την πιστωτική του πελάτη και κάνει τα εισιτήρια μη-διαθέσιμα
     * Αν επιστρέψει false τότε κάτι πήγε στραβά (π.χ. ανεπαρκείς υπόλοιπο, δεν υπάρχουν θέσεις κλπ)
     */
    public boolean createReservation(UUID customerID, UUID eventID, int ticketCount, boolean isVip) {
        boolean enoughAvailableTickets = getAvailableTicketAmount(eventID, isVip) >= ticketCount;
        ArrayList<Ticket> availableTickets = db.getTicketsTable().getAvailableTickets(eventID, isVip);
        ArrayList<Ticket> toBePurchased = new ArrayList<>();
        double cost = 0;

        if (enoughAvailableTickets) {
            while (toBePurchased.size() < ticketCount) {
                Ticket cur = availableTickets.removeFirst();
                toBePurchased.add(cur);
                cost += cur.getPrice();
            }

            /* Remove balance and make purchased tickets unavailable */
            UUID reservationID = UUID.randomUUID();

            db.getReservationsTable().insertReservation(reservationID, customerID, eventID, ticketCount, cost);

            for (Ticket ticket : toBePurchased) {
                db.getTicketsTable().bindTicket(ticket.getTicketID(), reservationID);
            }

            return true;

        }

        return false;
    }

    /*
     * Ακύρωση κρατησης, επιστροφή ποσοστό των χρημάτων (π.χ. 0.8f = 80%) και τα εισιτήρια γίνονται διαθέσιμα ξανά
     * Αν επιστρέψει true, ακυρώθηκε σωστά η κράτηση αλλιώς false αν κάτι πήγε στραβά
     */
    public double cancelReservations(UUID customerId, UUID eventId) {
        List<Reservation> reservations = db.getReservationsTable().getReservationsByCustomerAndEvent(customerId, eventId);
        double sum = 0;
        for (Reservation r : reservations) {
            sum += cancelReservation(r.getReservationID());
        }
        return sum;
    }

    public double cancelReservation(UUID reservationID) {
        // ArrayList<Ticket> reservedTickets = db.getTicketsTable().getReservedTickets(reservationID); // ???
        Reservation reservation = db.getReservationsTable().getReservation(reservationID);

        db.getTicketsTable().unbindTickets(reservationID);

        // Επιστροφή χρημάτων
        double refundedAmount = 1;

        // Αν το event δεν ειναι flagged ως cancelled (capacity 0) τοτε επιστρεφουμε 50% των χρηματων
        if (db.getEventsTable().getEvent(reservation.getEventID()).getCapacity() != 0) {
            refundedAmount = 0.5;
        }
        // Αφαίρεσει reservation απο το reservations table
        db.getReservationsTable().removeReservation(reservationID);

        return reservation.getPaymentAmount() * refundedAmount;
    }

    /*
     * Ακύρωση event με eventID, ταυτόχρονα ακυρώνει κρατήσεις, διαγράφει εισιτήρια και αφαιρεί το event (σαν να μην υπήρχε ποτέ το event)
     */
    public double cancelEvent(UUID eventID) {
        ArrayList<Reservation> reservations = db.getReservationsTable().getReservationsByEvent(eventID);
        
        db.getEventsTable().flagEventAsCancelled(eventID);
        
        double sum = 0;
        for (Reservation r : reservations) {
            sum += cancelReservation(r.getReservationID());
        }

        db.getTicketsTable().deleteTicketsFromEvent(eventID);
        
        db.getEventsTable().removeEvent(eventID);
        
        return sum;
    }

    /*
     * Επιστρέφει events που δεν έχουν γίνει ακόμα, δηλαδή event_date > current_date
     */
    public List<Event> getAvailableEvents() {
        try {
            return db.getEventsTable().getAllEvents(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Αυτή η συνάρτηση πρέπει να χρησιμοποιηθεί μετά απο επιβεβαίωση πως υπάρχουν αρκετά ticket
     * Επιστρέφει -1 αν δεν υπάρχουν αρκετά εισιτήρια, αλλιώς επιστρέφει το κόστος
     */
    public double calculateReservationCost(UUID eventID, int ticketCount, boolean isVip) {
        ArrayList<Ticket> availableTickets = db.getTicketsTable().getAvailableTickets(eventID, isVip);
        double cost = 0;

        if (availableTickets.size() < ticketCount) {
            return -1;
        }

        for (int i = 0; i < ticketCount; i++) {
            cost += availableTickets.get(i).getPrice();
        }

        return cost;
    }
    
    public Event getEventWithMostIncomeInDateRange(LocalDate d1, LocalDate d2){
        return db.getEventsTable().getEventWithMostIncomeInDateRange(d1, d2);
    }
    
    public ArrayList<Reservation> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return db.getReservationsTable().getReservationsByDateRange(startDate, endDate);
    }
    /*
     * Επιστρέφει το Event με τις περισσότερες κρατήσεις
     */
    public Event getMostReservedEvent() {
        return db.getReservationsTable().getMostReservedEvent();
    }

    /*
     * Επιστρέφει τα συνολικά έσοδα απο μία εκδήλωση, δηλαδή όλα τα έσοδα απο τις κρατήσεις
     */
    public int getTotalEarnings(UUID eventID) {
        return db.getReservationsTable().getTotalEarnings(eventID);
    }

    /*
     * Θα πρότεινα το calculateReservationCost() αντί για αυτή την συνάρτηση
     */
    public double getTicketPrice(UUID eventId, boolean isVip) {
        return db.getTicketsTable().getTicketPrice(eventId, isVip);
    }

    /*
     * Returns the expected refund amount, which is different if the whole event is cancelled or just the reservation
     */
    public int calculateRefund(UUID reservationID, boolean isEventCancelled) {
        float refundRate = isEventCancelled ? 1.0f : 0.8f;
        return db.getReservationsTable().calculateRefund(reservationID, refundRate);
    }
    
     public double getTotalVipSales() {
         return db.getTicketsTable().getTotalVipSales();
     }
}
