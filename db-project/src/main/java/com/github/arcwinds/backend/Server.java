package com.github.arcwinds.backend;

import com.github.arcwinds.common.Event;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Server {
    Database db;

    public Server(){
        try {
            db = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Database getDb(){
        return this.db;
    }

    public boolean loginUser(String email, String password) {
        return db.getCustomersTable().authenticateCustomer(email, password);
    }

    //epeidi to parathuro den kserei gia tin basi dedomenwn poy einai ston server
    public List<Event> getAllEvents() { //new
        try { //new
            return db.getEventsTable().getAllEvents(); //new
        } catch (SQLException e) { //new
            throw new RuntimeException(e); //new
        } //new
    } //new

    public boolean addCustomer(UUID customer_id, String full_name, String email, String password) {
        return db.getCustomersTable().insertCustomer(customer_id, full_name, email, password);
    }

    public boolean loginAdmin(String username, String password, String idNumber, String phoneNumber) {
        return db.getAdminsTable().authenticateAdmin(username, password, idNumber, phoneNumber);
    }

    public boolean addEvent(UUID event_id, String name, LocalDate date, String time, String eventType, int capacity) {
        return db.getEventsTable().insertEvent(event_id, name, date, time, eventType, capacity);
    }

    //auti i sunartisi den pernei tipota kai epistrefei mia lista me ta availableevents
    public List<Event> getAvailableEvents() {
        try {
            return db.getEventsTable().getAllEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double calculateReservationCost(UUID eventId, int ticketCount, boolean is_vip){
        double ticketPrice = db.getTicketsTable().getTicketPrice(eventId, is_vip); //new
        return ticketPrice * ticketCount; // Υπολογισμός συνολικού κόστους //new
    }

    public boolean createReservation(UUID customerId, UUID eventId, int ticketCount, double paymentAmount, boolean is_vip) {
        // return db.. ....

        // paw na kanw krathsh se ena event poy exoyn meinei 3 theseis
        // an egw valw ticketCount = 4, ti ginetai?

        // 
        if(db.getEventsTable().getEventRemainingCapacity(eventId, is_vip) >= ticketCount){
            double cost = calculateReservationCost(eventId, ticketCount, is_vip);
            db.getReservationsTable().createReservation(customerId, eventId, ticketCount, cost);
            //prepei i createReservation na kanei update kai ta eisitiria!
            return true;
        }

        return false;
    }

    public int getAvailableSeats(UUID eventId, boolean is_vip) { 
        return db.getEventsTable().getEventRemainingCapacity(eventId, is_vip);
    }
    
    public double getTicketPrice(UUID eventId, boolean is_vip) { 
        return db.getTicketsTable().getTicketPrice(eventId, is_vip);
    }

    public int setEventRemainingCapacity(UUID eventId, boolean is_vip,int ticketCount){
        return 0;
    }

    public int cancelReservation(UUID reservationId, UUID eventId, int ticketCount, boolean isVip) {
        return 0; //db.cancelReservation(reservationId, eventId, ticketCount, isVip);
    }

    public double calculateRefund(UUID reservationId) {
        return 0; //db.calculateRefund(reservationId);
    }
}
