package com.github.arcwinds.common;

public class Ticket {
    private int ticketId;
    private int eventId;
    private String seatType;
    private double price;
    private boolean availability;

   
    public Ticket(int ticketId, int eventId, String seatType, double price, boolean availability) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.seatType = seatType;
        this.price = price;
        this.availability = availability;
    }

    
    public int getTicketId() {
        return ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailability() {
        return availability;
    }

    
}
