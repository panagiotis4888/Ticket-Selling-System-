package com.github.arcwinds.frontend;

import com.github.arcwinds.backend.Server;
import com.github.arcwinds.common.Customer;
import com.github.arcwinds.frontend.pages.*;
import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.Reservation;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.*;

public class MainFrame extends JFrame {

    Server server_ref;
    public static UUID login_user;

    public HashMap<String, Object> session = new HashMap<>();

    public MainFrame(Server server_ref) {
        this.server_ref = server_ref;

        initGui();

        setTitle("Event booking app");
        setSize(800, 1000);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGui() {
        setPage("home");
    }

    public void setPage(String name_of_page) {
        final JPanel page;
        page = switch (name_of_page) {
            case "home" ->
                new HomePage(this);
            case "register" ->
                new RegisterPage(this);
            case "login" ->
                new LoginPage(this);
            case "dashboard" ->
                new Dashboard(this);
            case "about" ->
                new AboutPage(this);
            case "adminlogin" ->
                new AdminLoginPage(this);
            case "admindashboard" ->
                new AdminDashboard(this);
            case "addevent" ->
                new AdminAddEvent(this);
            case "cancelevent" ->
                new AdminCancelEvent(this);
            case "calculatesales" ->
                new AdminEventSales(this);
            default ->
                null;
        };

        SwingUtilities.invokeLater(() -> {
            setContentPane(page);
            revalidate();
            repaint();
        });
    }

    public boolean registerCustomer(String full_name, String email, String password) {
        return server_ref.addCustomer(UUID.randomUUID(), full_name, email, password);
    }

    public boolean loginUser(String email, String password) {

        Customer c = server_ref.loginUser(email, password);
        if (c != null) {
            session.put("user", c);
            return true;
        }

        return false;
    }

    public boolean loginAdmin(String username, String password) {
        return server_ref.loginAdmin(username, password);
    }

    public boolean addEvent(String name, String date, String time, String eventType,
            int capacity, int vipTickets, int normalPrice, int vipPrice) {
        return server_ref.addEvent(name, date, time, eventType, capacity, vipTickets, normalPrice, vipPrice);
    }

    public List<Event> getAvailableEvents() {
        return server_ref.getAvailableEvents();
    }

    public boolean createReservation(UUID customerId, UUID eventId, int ticketCount, boolean is_vip) {
        return server_ref.createReservation(customerId, eventId, ticketCount, is_vip);
    }

    public int getAvailableSeats(UUID eventId, boolean is_vip) { //new
        return server_ref.getAvailableTicketAmount(eventId, is_vip); //new
    }

    public double getTicketPrice(UUID eventId, boolean is_vip) { //new
        return server_ref.getTicketPrice(eventId, is_vip); //new
    }

    public double cancelReservations(UUID eventId) {
        return server_ref.cancelReservations(((Customer) session.get("user")).getCustomerId(), eventId);
    }

    public int calculateRefund(UUID reservationId, boolean isEventCancelled) {
        return server_ref.calculateRefund(reservationId, isEventCancelled);
    }

    public double cancelEvent(UUID selectedEventId) {
        return server_ref.cancelEvent(selectedEventId);
    }

    public String getCurrentCustomerName() {
        return ((Customer) session.get("user")).getName();
    }

    public double getEventSales(UUID eventid) {
        return server_ref.getTotalEarnings(eventid);
    }

    public Event getMostPopularEvent() {
        return server_ref.getMostReservedEvent();
    }

    public Event getEventWithMostIncomeInDateRange(LocalDate d1, LocalDate d2) {
        return server_ref.getEventWithMostIncomeInDateRange(d1, d2);
    }

    public ArrayList<Reservation> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return server_ref.getReservationsByDateRange(startDate, endDate);
    }

    public double getTotalVipSales() {
        return server_ref.getTotalVipSales();
    }
}
