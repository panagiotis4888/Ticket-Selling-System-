package com.github.arcwinds.common;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private final UUID reservationID;
    private final LocalDate reservationDate;
    private final int ticketCount;
    private final double paymentAmount;
    private final UUID customerID;
    private final UUID eventID;

    public Reservation(UUID reservationID, LocalDate reservationDate, int ticketCount, double paymentAmount, UUID customerID, UUID eventID) {
        this.reservationID = reservationID;
        this.reservationDate = reservationDate;
        this.ticketCount = ticketCount;
        this.paymentAmount = paymentAmount;
        this.customerID = customerID;
        this.eventID = eventID;
    }

    public UUID getReservationID() {
        return reservationID;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public UUID getEventID() {
        return eventID;
    }
}
