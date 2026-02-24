package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class ReservationsTable {
    private final Database db;

    public ReservationsTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String reservationTableQuery = "CREATE TABLE IF NOT EXISTS reservations(" +
                    "reservation_id varchar(36) NOT NULL, reservation_date DATETIME NOT NULL, " +
                    "ticket_count int NOT NULL, payment_amount int NOT NULL, " +
                    "customer_id varchar(36) NOT NULL, event_id varchar(36) NOT NULL," +
                    "PRIMARY KEY(reservation_id), " +
                    "FOREIGN KEY(customer_id) REFERENCES customers(customer_id), " +
                    "FOREIGN KEY(event_id) REFERENCES events(event_id)" +
                    ")";

            statement.execute(reservationTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Reservations table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // prepei na kanei update ta antistoixa eishthria
    public boolean createReservation(UUID customerID, UUID eventID, int ticketCount, double paymentAmount) {
        String query = "INSERT INTO reservations(reservation_id, reservation_date, ticket_count, payment_amount, customer_id, event_id) VALUES (?, CURDATE(), ?, ?, ?, ?)";
        String updateCapacityQuery = "UPDATE events SET capacity = capacity - ? WHERE event_id = ?";

        try (
                PreparedStatement stmt = db.getConnection().prepareStatement(query);
                PreparedStatement updateStmt = db.getConnection().prepareStatement(updateCapacityQuery)
        ) {

            stmt.setString(1, String.valueOf(UUID.randomUUID()));
            stmt.setInt(2, ticketCount);
            stmt.setDouble(3, paymentAmount);
            stmt.setString(4, customerID.toString());
            stmt.setString(5, eventID.toString());
            int rowsInserted = stmt.executeUpdate();


            updateStmt.setInt(1, ticketCount);
            updateStmt.setString(2, eventID.toString());
            int rowsUpdated = updateStmt.executeUpdate();

            return rowsInserted > 0 && rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    //new QUERIES πιθανόν χρειάζονται αλλαγες
//    public boolean cancelReservation(UUID reservationId, UUID eventId, int ticketCount, boolean isVip) {
//        //λογικα δουλεύουν
//        String deleteReservationQuery = "DELETE FROM reservations WHERE reservation_id = ?";
//        String updateCapacityQuery = "UPDATE events SET capacity = capacity + ? WHERE event_id = ?";
//
//        try (
//                PreparedStatement deleteStmt = getConnection().prepareStatement(deleteReservationQuery); PreparedStatement updateStmt = getConnection().prepareStatement(updateCapacityQuery);) {
//
//            deleteStmt.setString(1, reservationId.toString());
//            int rowsDeleted = deleteStmt.executeUpdate();
//
//            updateStmt.setInt(1, ticketCount);
//            updateStmt.setString(2, eventId.toString());
//            int rowsUpdated = updateStmt.executeUpdate();
//
//            return rowsDeleted > 0 && rowsUpdated > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public double calculateRefund(UUID reservationId) {
//        String query = "SELECT payment_amount FROM reservations WHERE reservation_id = ?";
//        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
//            stmt.setString(1, reservationId.toString());
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                double originalPayment = rs.getDouble("payment_amount");
//                return originalPayment * 0.8;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return 0.0;
//    }
}
