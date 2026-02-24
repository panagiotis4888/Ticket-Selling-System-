package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class TicketsTable {
    private final Database db;

    public TicketsTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String ticketTableQuery = "CREATE TABLE IF NOT EXISTS tickets(" +
                    "ticket_id varchar(36) NOT NULL, price decimal(10,2) NOT NULL, " +
                    "availability tinyint(1) NOT NULL DEFAULT 1, is_vip BOOLEAN NOT NULL," +
                    "event_id varchar(36) NOT NULL, reservation_id varchar(36)," +
                    "PRIMARY KEY(ticket_id), " +
                    "FOREIGN KEY(event_id) REFERENCES events(event_id)," +
                    "FOREIGN KEY(reservation_id) REFERENCES reservations(reservation_id)" +
                    ")";

            statement.execute(ticketTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Tickets table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getTicketPrice(UUID eventId, boolean isVip) {
        String query = "SELECT price FROM tickets WHERE event_id = ? AND is_vip = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventId.toString());
            stmt.setBoolean(2, isVip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1); // Επιστροφή της τιμής
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Διαχείριση σφάλματος
        }
        return 0.0; // Επιστροφή 0.0 αν αποτύχει
    }

    /*
     * Returns the amount of available tickets for eventID, also uses vipSeat to check the seat type
     */
    public int getAvailableTickets(UUID eventID, boolean vipSeat) {
        String query = "SELECT COUNT(*) FROM tickets WHERE event_id = ? AND is_vip = ? AND availability = 1";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventID.toString());
            stmt.setBoolean(2, vipSeat);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Επιστροφή της τιμής
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Διαχείριση σφάλματος
        }

        return 0;
    }

    /*
     * Create tickets that are available by default for a specific event
     */
    public void createTickets(UUID eventID, boolean vipSeat, int price, int amount){
        String query = "INSERT INTO tickets" +
                "(ticket_id, price, availability, is_vip, event_id, reservation_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            for(int i=0; i<amount; i++){
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setBigDecimal(2, BigDecimal.valueOf(price));
                stmt.setInt(3, 1);
                stmt.setBoolean(4, vipSeat);
                stmt.setString(5, eventID.toString());
                stmt.setString(6, null);

                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
