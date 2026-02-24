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
                    "card_number varchar(16) NOT NULL," +
                    "PRIMARY KEY(customer_id)," +
                    "FOREIGN KEY(card_number) REFERENCES credit_cards(card_number)" +
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
        String query = "INSERT INTO customers(customer_id, full_name, email, password, card_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, customer_id.toString());
            stmt.setString(2, full_name);
            stmt.setString(3, email);
            stmt.setString(4, password);

            /* Για λίγο θα μείνει αυτό εδώ μέχρι να αλλάξουν οι παράμετροι με στοιχεία cc κατα την εγγραφή */
            stmt.setString(5, db.getCreditCardsTable().createRandom().getCardNumber());
            /* End */

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; //Επιστρέφει true αν έγινε επιτυχής εισαγωγή
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Returns true if user exists, otherwise false
     */
    public boolean authenticateCustomer(String name, String password) {
        String query = "SELECT COUNT(*) FROM Customers WHERE full_name = ? AND password = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
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
                        rs.getString("email"),
                        rs.getString("card_number")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /*
     * Returns the balance of the customer's credit card
     * Returns -1 if anything goes wrong (no customer by that id or credit card number is invalid)
     */
    public int getBalance(UUID customerID){
        Customer customer = getCustomerByID(customerID);

        if(customer != null){
            return db.getCreditCardsTable().getBalance(customer.getCreditCard());
        }

        return -1;
    }

    public void setBalance(UUID customerID, int newBalance){
        Customer customer = getCustomerByID(customerID);

        if(customer != null){
            db.getCreditCardsTable().setBalance(customer.getCreditCard(), newBalance);
        }
    }
}
