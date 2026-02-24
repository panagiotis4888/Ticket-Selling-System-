package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.Customer;
import com.github.arcwinds.common.Ticket;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class TicketsTable {

    private final Database db;

    public TicketsTable(Database db) {
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String ticketTableQuery = "CREATE TABLE IF NOT EXISTS tickets("
                    + "ticket_id varchar(36) NOT NULL, price DECIMAL(10,2) NOT NULL, "
                    + "availability BOOLEAN NOT NULL DEFAULT TRUE, is_vip BOOLEAN NOT NULL,"
                    + "event_id varchar(36) NOT NULL, reservation_id varchar(36),"
                    + "PRIMARY KEY(ticket_id), "
                    + "FOREIGN KEY(event_id) REFERENCES events(event_id),"
                    + "FOREIGN KEY(reservation_id) REFERENCES reservations(reservation_id)"
                    + ")";

            statement.execute(ticketTableQuery);
            statement.close();

            if (DEBUG_MODE) {
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
        return 0.0; // Επιστροφdή 0.0 αν αποτύχει
    }

    /*
     * Returns the amount of available tickets for eventID, also uses vipSeat to check the seat type
     */
    public int getAvailableTicketAmount(UUID eventID, boolean vipSeat) {
        String query = "SELECT COUNT(*) FROM tickets WHERE event_id = ? AND is_vip = ? AND availability = true";

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
     * Returns the Ticket objects of all available tickets
     */
    public ArrayList<Ticket> getAvailableTickets(UUID eventID, boolean vipSeat) {
        String query = "SELECT * FROM tickets WHERE event_id = ? AND is_vip = ? AND availability = true";
        ArrayList<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventID.toString());
            stmt.setBoolean(2, vipSeat);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(
                        UUID.fromString(rs.getString(1)),
                        rs.getInt(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        UUID.fromString(rs.getString(5)),
                        null
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }

    /*
     * Create tickets that are available by default for a specific event
     */
    public void createTickets(UUID eventID, boolean vipSeat, int price, int amount) {
        String query = "INSERT INTO tickets"
                + "(ticket_id, price, availability, is_vip, event_id, reservation_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            for (int i = 0; i < amount; i++) {
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setBigDecimal(2, BigDecimal.valueOf(price));
                stmt.setBoolean(3, true);
                stmt.setBoolean(4, vipSeat);
                stmt.setString(5, eventID.toString());
                stmt.setString(6, null);

                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Sets a ticket's reservationID and makes it unavailable
     */
    public void bindTicket(UUID ticketID, UUID reservationID) {
        String query = "UPDATE tickets SET availability = false, reservation_id = ? WHERE ticket_id = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, reservationID.toString());
            stmt.setString(2, ticketID.toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Make a ticket available and remove its reservationID
     */
    public void unbindTickets(UUID reservationId) {
        String query = "UPDATE tickets SET availability = true, reservation_id = ? WHERE reservation_id = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, null);
            stmt.setString(2, reservationId.toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Gets all tickets reserved with reservationID
     */
    public boolean deleteTicketsFromEvent(UUID eventID) {
        String query = "DELETE FROM tickets WHERE event_id = ?";

        try (PreparedStatement deleteStmt = db.getConnection().prepareStatement(query)) {
            deleteStmt.setString(1, eventID.toString());
            deleteStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getTotalVipSales() {
        String query = "SELECT SUM(price) FROM tickets WHERE is_vip = TRUE AND reservation_id IS NOT NULL";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getBigDecimal(1) != null) {
                return rs.getBigDecimal(1).doubleValue();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0.0;
    }
}
