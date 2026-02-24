package com.github.arcwinds.common;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Event {
    private UUID eventID;        // ID του Event
    private String name;        // Όνομα του Event
    private LocalDate dateTime;          // Ημερομηνία διεξαγωγής
    private LocalTime eventTime;
    private EventType eventType;   // Τύπος Event (π.χ., "Concert", "Theater")
    private int capacity;       // Χωρητικότητα

    public Event(UUID eventID, String name, LocalDate dateTime, LocalTime eventTime, EventType eventType, int capacity) {
        this.eventID = eventID;
        this.name = name;
        this.dateTime = dateTime;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.capacity = capacity;
    }

    public UUID getEventID() {
        return eventID;
    }
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    
}
