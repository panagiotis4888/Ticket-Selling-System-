package com.github.arcwinds.common;

import java.util.UUID;

public class Ticket {
    private final UUID ticketID;
    private final double price;
    private boolean availability;
    private final boolean isVip;
    private final UUID eventID;
    private UUID reservationID;

    public Ticket(UUID ticketID, double price, boolean availability, boolean isVip, UUID eventID, UUID reservationID) {
        this.ticketID = ticketID;
        this.price = price;
        this.availability = availability;
        this.isVip = isVip;
        this.eventID = eventID;
        this.reservationID = reservationID;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public boolean isVip() {
        return isVip;
    }

    public UUID getEventID() {
        return eventID;
    }

    public UUID getReservationID() {
        return reservationID;
    }
}
