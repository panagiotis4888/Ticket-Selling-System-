package com.github.arcwinds.common;
import java.time.LocalDateTime;
import java.util.Date;
public class Reservation {
    private int reservationId;
    private int customerId;
    private int eventId;
    private int ticketId;
    private LocalDateTime reservationDate;
    private int ticketCount;
    private double paymentAmount;

    // Constructor
    public Reservation(int reservationId, int customerId, int eventId, int ticketId, LocalDateTime reservationDate, int ticketCount, double paymentAmount) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.eventId = eventId;
        this.ticketId = ticketId;
        this.reservationDate = reservationDate;
        this.ticketCount = ticketCount;
        this.paymentAmount = paymentAmount;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }



}
