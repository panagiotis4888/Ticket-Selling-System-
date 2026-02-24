package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class CustomersTable {
    private final Database db;

    public CustomersTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String customerTableQuery = "CREATE TABLE IF NOT EXISTS customers(" +
                    "customer_id varchar(36) NOT NULL, full_name varchar(255) NOT NULL, " +
                    "email varchar(255) NOT NULL, password varchar(50) NOT NULL," +
                    "card_number varchar(16), " +
                    "PRIMARY KEY(customer_id)" +
                    ")";

            statement.execute(customerTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Customers table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertCustomer(UUID customer_id, String full_name, String email, String password) {
        String query = "INSERT INTO customers(customer_id, full_name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, customer_id.toString());
            stmt.setString(2, full_name);
            stmt.setString(3, email);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; //Επιστρέφει true αν έγινε επιτυχής εισαγωγή
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Returns true if user exists, otherwise false
     */
    public Customer authenticateCustomer(String name, String password) {
        String query = "SELECT * FROM Customers WHERE full_name = ? AND password = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(UUID.fromString(rs.getString(1)), rs.getString(2), "hidden", rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /*
     * Returns Customer object if UUID is valid, otherwise null
     */
    public Customer getCustomerByID(UUID customerID) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, customerID.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        UUID.fromString(rs.getString("customer_id")),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
