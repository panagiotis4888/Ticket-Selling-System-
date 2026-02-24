package com.github.arcwinds.backend;

import com.github.arcwinds.backend.tables.*;

import java.sql.*;

import static com.github.arcwinds.Main.DEBUG_MODE;

public class Database {
    private Connection connection;
    private final EventsTable eventsTable;
    private final AdminsTable adminsTable;
    private final CustomersTable customersTable;
    private final ReservationsTable reservationsTable;
    private final TicketsTable ticketsTable;
    private final CreditCardsTable creditCardsTable;

    public Database() throws SQLException{
        creditCardsTable = new CreditCardsTable(this);
        eventsTable = new EventsTable(this);
        adminsTable = new AdminsTable(this);
        customersTable = new CustomersTable(this);
        reservationsTable = new ReservationsTable(this);
        ticketsTable = new TicketsTable(this);
    }

    public Connection getConnection() throws SQLException{
        if(connection!=null && connection.isValid(5)){
            return connection;
        }

        String url = "jdbc:mysql://localhost:3306/ticket_booking_system";
        String user = "root";
        String password = "";

        this.connection = DriverManager.getConnection(url, user, password);
        if(DEBUG_MODE){
            System.out.println("Connected to the ticket system's database");
        }

        return connection;
    }

    public void closeConnection(){
        try {
            getConnection().close();
        } catch (SQLException e) {
            System.err.println("Error on connection shutdown!");
            throw new RuntimeException(e);
        }
    }

    public EventsTable getEventsTable() {
        return eventsTable;
    }

    public AdminsTable getAdminsTable() {
        return adminsTable;
    }

    public CustomersTable getCustomersTable() {
        return customersTable;
    }

    public ReservationsTable getReservationsTable() {
        return reservationsTable;
    }

    public TicketsTable getTicketsTable() {
        return ticketsTable;
    }

    public CreditCardsTable getCreditCardsTable(){
        return creditCardsTable;
    }
}