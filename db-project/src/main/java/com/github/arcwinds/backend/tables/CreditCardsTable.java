package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;
import com.github.arcwinds.common.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class CreditCardsTable {
    private final Database db;
    private final Random rnd = new Random();

    public CreditCardsTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String creditCardTableQuery = "CREATE TABLE IF NOT EXISTS credit_cards" +
                    "(card_number varchar(16) NOT NULL, expiration_date DATE NOT NULL, " +
                    "cvv varchar(3) NOT NULL, balance int NOT NULL," +
                    "PRIMARY KEY(card_number)" +
                    ")";

            statement.execute(creditCardTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Credit_cards table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Creates a credit card in the credit_cards table
     */
    public boolean create(String cardNumber, String expiration_date, String cvv, int balance){
        String query = "INSERT INTO credit_cards(card_number, expiration_date, cvv, balance) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, cardNumber);
            stmt.setDate(2, Date.valueOf(expiration_date));
            stmt.setString(3, cvv);
            stmt.setInt(4, balance);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Returns the balance of a credit card number, if not found then it returns -1
     */
    public int getBalance(String cardNumber){
        String query = "SELECT balance FROM credit_cards WHERE card_number = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();

            if(rs != null){
                return rs.getInt(1);
            } else{
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Change a credit card's balance
     */
    public void setBalance(String cardNumber, int newBalance){
        String query = "UPDATE credit_cards SET balance = ? WHERE card_number = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setInt(1, newBalance);
            stmt.setString(2, cardNumber);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CreditCard> getAll(){
        String query = "SELECT * FROM credit_cards";
        ArrayList<CreditCard> creditCards = new ArrayList<>(0);

        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CreditCard creditCard = new CreditCard(
                        rs.getString("card_number"),
                        rs.getDate("expiration_date").toLocalDate(),
                        rs.getString("cvv"),
                        rs.getInt("balance")
                );
                creditCards.add(creditCard);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return creditCards;
    }

    public CreditCard createRandom(){
        String cardNumber = "00000000000%d".formatted(rnd.nextInt(10000, 100000));
        String expirationDate = new Date(System.currentTimeMillis() + rnd.nextLong()).toString();
        String cvv = "%d%d%d".formatted(rnd.nextInt(10), rnd.nextInt(10), rnd.nextInt(10));
        int balance = rnd.nextInt(1000);

        create(cardNumber, expirationDate, cvv, balance);

        return new CreditCard(
                cardNumber,
                Date.valueOf(expirationDate).toLocalDate(),
                cvv,
                balance
        );
    }
}
