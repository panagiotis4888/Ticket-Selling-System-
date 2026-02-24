package com.github.arcwinds.backend.tables;

import com.github.arcwinds.backend.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class AdminsTable {
    private final Database db;

    public AdminsTable(Database db){
        this.db = db;

        try {
            Statement statement = db.getConnection().createStatement();

            String adminTableQuery = "CREATE TABLE IF NOT EXISTS admins(" +
                    "admin_id varchar(36) NOT NULL, username varchar(25) NOT NULL," +
                    "password varchar(30) NOT NULL, id_number varchar(30) NOT NULL, " +
                    "phone_number varchar(15) NOT NULL, " +
                    "PRIMARY KEY(admin_id)" +
                    ")";

            statement.execute(adminTableQuery);
            statement.close();

            if(DEBUG_MODE){
                System.out.println("Admins table has been created.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createAdmin(UUID adminID, String username, String password, String idNumber, String phoneNumber) {
        String query = "INSERT INTO admins(admin_id, username, password, id_number, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, adminID.toString());
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, idNumber);
            stmt.setString(5, phoneNumber);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean authenticateAdmin(String username, String password, String idNumber, String phoneNumber) {
        String query = "SELECT COUNT(*) FROM admins WHERE username = ? AND password = ? AND id_number = ? AND phone_number = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, idNumber);
            stmt.setString(4, phoneNumber);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
