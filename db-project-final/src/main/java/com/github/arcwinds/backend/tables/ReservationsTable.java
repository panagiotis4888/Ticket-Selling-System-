package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;
import java.sql.Date;
import java.time.LocalDate;

public class ReservationsTable {

    private final Database db;

    public ReservationsTable(Database db) {
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String reservationTableQuery = "CREATE TABLE IF NOT EXISTS reservations("
                    + "reservation_id varchar(36) NOT NULL, reservation_date DATE NOT NULL, "
                    + "ticket_count int NOT NULL, payment_amount DECIMAL(10,2) NOT NULL, "
                    + "customer_id varchar(36) NOT NULL, event_id varchar(36) NOT NULL,"
                    + "PRIMARY KEY(reservation_id), "
                    + "FOREIGN KEY(customer_id) REFERENCES customers(customer_id), "
                    + "FOREIGN KEY(event_id) REFERENCES events(event_id)"
                    + ")";

            statement.execute(reservationTableQuery);
            statement.close();

            if (DEBUG_MODE) {
                System.out.println("Reservations table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Inserts a reservation row, used within Server:createReservation() which handles additional logic
     * Shouldn't be used without considering the effect it should have on the tickets table
     */
    public void insertReservation(UUID id, UUID customerID, UUID eventID, int ticketCount, double paymentAmount) {
        String query = "INSERT INTO reservations(reservation_id, reservation_date, ticket_count, payment_amount,"
                + " customer_id, event_id) VALUES (?, CURDATE(), ?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, id.toString());
            stmt.setInt(2, ticketCount);
            stmt.setDouble(3, paymentAmount);
            stmt.setString(4, customerID.toString());
            stmt.setString(5, eventID.toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation getReservation(UUID reservationID) {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, reservationID.toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        reservationID,
                        rs.getDate(2).toLocalDate(),
                        rs.getInt(3),
                        rs.getDouble(4),
                        UUID.fromString(rs.getString(5)),
                        UUID.fromString(rs.getString(6))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /*
     * Returns all reservations for a specific event
     */
    public ArrayList<Reservation> getReservationsByEvent(UUID eventID) {
        String query = "SELECT * FROM reservations WHERE event_id = ?";
        ArrayList<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventID.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        UUID.fromString(rs.getString(1)),
                        rs.getDate(2).toLocalDate(),
                        rs.getInt(3),
                        rs.getInt(4),
                        UUID.fromString(rs.getString(5)),
                        UUID.fromString(rs.getString(6))
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }

    public ArrayList<Reservation> getReservationsByCustomerAndEvent(UUID customerId, UUID eventID) {
        String query = "SELECT * FROM reservations WHERE customer_id = ? AND event_id = ?";
        ArrayList<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, customerId.toString());
            stmt.setString(2, eventID.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        UUID.fromString(rs.getString(1)),
                        rs.getDate(2).toLocalDate(),
                        rs.getInt(3),
                        rs.getInt(4),
                        UUID.fromString(rs.getString(5)),
                        UUID.fromString(rs.getString(6))
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }

    public boolean removeReservation(UUID reservationID) {
        String query = "DELETE FROM reservations WHERE reservation_id = ?";

        try (PreparedStatement deleteStmt = db.getConnection().prepareStatement(query)) {
            deleteStmt.setString(1, reservationID.toString());
            int rowsDeleted = deleteStmt.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Returns the total earnings for an event
     */
    public int getTotalEarnings(UUID eventID) {
        String query = "SELECT SUM(payment_amount) FROM reservations WHERE event_id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, eventID.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    /*
     * Refunds the total amount to be refunded based on the refundRate chosen
     */
    public int calculateRefund(UUID reservationID, float refundRate) {
        return (int) (getReservation(reservationID).getPaymentAmount() * refundRate);
    }

    public Event getMostReservedEvent() {
        String query
                = "SELECT event_id, COUNT(*) AS total "
                + "FROM reservations "
                + "GROUP BY event_id "
                + "ORDER BY total DESC "
                + "LIMIT 1";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return db.getEventsTable().getEvent(UUID.fromString(rs.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public ArrayList<Reservation> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT reservation_id, reservation_date, ticket_count, payment_amount, customer_id, event_id "
                + "FROM reservations "
                + "WHERE reservation_date BETWEEN ? AND ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        UUID.fromString(rs.getString("reservation_id")),
                        rs.getDate("reservation_date").toLocalDate(),
                        rs.getInt("ticket_count"),
                        rs.getDouble("payment_amount"),
                        UUID.fromString(rs.getString("customer_id")),
                        UUID.fromString(rs.getString("event_id"))
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }
}
