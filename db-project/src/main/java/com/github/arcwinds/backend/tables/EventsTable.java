package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.EventType;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;
import java.time.LocalDate;
import java.util.List;

public class EventsTable {
    private final Database db;

    public EventsTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String eventTableQuery = "CREATE TABLE IF NOT EXISTS events" +
                    "(event_id varchar(36) NOT NULL, name varchar(50) NOT NULL, event_date DATE NOT NULL, " +
                    "event_time TIME NOT NULL, event_type int NOT NULL, capacity int NOT NULL," +
                    "PRIMARY KEY(event_id)" +
                    ")";

            statement.execute(eventTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Events table has been created.");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertEvent(UUID event_id, String name, LocalDate date, String time, String eventType, int capacity) {
        String query = "INSERT INTO events(event_id, name, event_date, event_time, event_type, capacity) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, event_id.toString());
            stmt.setString(2, name);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, Time.valueOf(time));
            stmt.setInt(5, EventType.valueOf(eventType).ordinal());
            stmt.setInt(6, capacity);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Event getEvent(UUID event_id) throws SQLException{
        Event event;
        PreparedStatement statement = db.getConnection().prepareStatement("SELECT * FROM events WHERE event_id = ?");
        statement.setString(1, event_id.toString());

        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            event = new Event(
                    UUID.fromString(rs.getString(1)),
                    rs.getString(2),
                    rs.getDate(3).toLocalDate(),
                    rs.getTime(4).toLocalTime(),
                    EventType.values()[rs.getInt(5)],
                    rs.getInt(6)
            );
        } else{
            statement.close();
            return null;
        }

        statement.close();
        return event;
    }

    public List<Event> getAllEvents() throws SQLException { //new
        List<Event> events = new ArrayList<>(); //new
        String query = "SELECT * FROM events"; //new
        try (Statement stmt = db.getConnection().createStatement(); //new
                 ResultSet rs = stmt.executeQuery(query)) { //new
            while (rs.next()) { //new
                events.add(new Event( //new
                        UUID.fromString(rs.getString("event_id")), //new
                        rs.getString("name"), //new
                        rs.getDate("date").toLocalDate(), //new
                        rs.getTime("time").toLocalTime(), //new
                        EventType.values()[rs.getInt("event_type")], //new
                        rs.getInt("capacity") //new
                )); //new
            } //new
        } //new
        return events; //new
    } //new

    public int getEventRemainingCapacity(UUID eventId, boolean is_vip){
        String query = "SELECT capacity FROM events WHERE event_id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventId.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
