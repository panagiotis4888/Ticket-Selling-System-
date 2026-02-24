package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.EventType;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import java.time.LocalDate;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class EventsTable {

    private final Database db;

    public EventsTable(Database db) {
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String eventTableQuery = "CREATE TABLE IF NOT EXISTS events"
                    + "(event_id varchar(36) NOT NULL, name varchar(50) NOT NULL, event_date DATE NOT NULL, "
                    + "event_time TIME NOT NULL, event_type int NOT NULL, capacity int NOT NULL,"
                    + "PRIMARY KEY(event_id)"
                    + ")";

            statement.execute(eventTableQuery);
            statement.close();

            if (DEBUG_MODE) {
                System.out.println("Events table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertEvent(UUID id, String name, String date, String time, String type,
            int capacity, int vipTickets, int normalPrice, int vipPrice) {
        String query = "INSERT INTO events(event_id, name, event_date, event_time, event_type, capacity) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, id.toString());
            stmt.setString(2, name);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(time));
            stmt.setInt(5, EventType.valueOf(type).ordinal());
            stmt.setInt(6, capacity);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        db.getTicketsTable().createTickets(id, false, normalPrice, capacity - vipTickets);
        db.getTicketsTable().createTickets(id, true, vipPrice, vipTickets);
        return true;
    }

    public Event getEvent(UUID eventID) {
        Event event;
        try {
            PreparedStatement statement = db.getConnection().prepareStatement("SELECT * FROM events WHERE event_id = ?");
            statement.setString(1, eventID.toString());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                event = new Event(
                        UUID.fromString(rs.getString(1)),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getTime(4).toLocalTime(),
                        EventType.values()[rs.getInt(5)],
                        rs.getInt(6)
                );
            } else {
                statement.close();
                return null;
            }

            statement.close();
            return event;
        } catch (SQLException e) {
            return null;
        }
    }

    public Event getEventWithMostIncomeInDateRange(LocalDate startDate, LocalDate endDate) {
        String query
                = "SELECT r.event_id, SUM(r.payment_amount) AS total_income "
                + "FROM reservations r "
                + "JOIN events e ON r.event_id = e.event_id "
                + "WHERE e.event_date BETWEEN ? AND ? "
                + "GROUP BY r.event_id "
                + "ORDER BY total_income DESC "
                + "LIMIT 1";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            // Convert LocalDate to SQL Date
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UUID eventID = UUID.fromString(rs.getString("event_id"));
                return getEvent(eventID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // If no events found within the range, return null
        return null;
    }

    public ArrayList<Event> getAllEvents(boolean available) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        if (available) {
            query += " WHERE event_date >= CURDATE()";
        }
        PreparedStatement statement = db.getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Event event = new Event(
                    UUID.fromString(rs.getString("event_id")),
                    rs.getString("name"),
                    rs.getDate("event_date").toLocalDate(),
                    rs.getTime("event_time").toLocalTime(),
                    EventType.values()[rs.getInt("event_type")],
                    rs.getInt("capacity")
            );
            events.add(event);
        }

        statement.close();
        return events;
    }

    public boolean removeEvent(UUID eventID) {
        String query = "DELETE FROM events WHERE event_id = ?";

        try (PreparedStatement deleteStmt = db.getConnection().prepareStatement(query)) {
            deleteStmt.setString(1, eventID.toString());
            int rowsDeleted = deleteStmt.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean flagEventAsCancelled(UUID eventID) {
        String query = "UPDATE events SET capacity = 0 WHERE event_id = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventID.toString());
            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
