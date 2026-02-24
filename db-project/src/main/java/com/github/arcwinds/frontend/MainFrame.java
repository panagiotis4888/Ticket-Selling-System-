package com.github.arcwinds.frontend;

import com.github.arcwinds.backend.Server;
import com.github.arcwinds.frontend.pages.*;
import com.github.arcwinds.common.Event;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.*;

public class MainFrame extends JFrame{
    Server server_ref;
    public static UUID login_user;
    HashMap<String, JPanel> pages_map = new HashMap<>();
    public HashMap<String, Object> session = new HashMap<>();

    public MainFrame(Server server_ref){
        this.server_ref = server_ref;

        initGui();

        setTitle("Event booking app");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGui(){
        pages_map.put("home", new HomePage(this));
        pages_map.put("register", new RegisterPage(this));
        pages_map.put("login", new LoginPage(this));
        pages_map.put("dashboard", new Dashboard(this));
        pages_map.put("about", new AboutPage(this));
        pages_map.put("adminPage", new AdminLoginPage(this));
        pages_map.put("adminDashboard", new AdminDashboard(this));
        pages_map.put("addevent", new AdminAddEvent(this));
        pages_map.put("eventsales", new EventSales(this));
        pages_map.put("mostpopular", new MostPopular(this));
        pages_map.put("timesales", new AdminTimeSales(this));
        pages_map.put("timereserve", new AdminTimeReserve(this));
        pages_map.put("vipsales", new AdminVipSales(this));

        setPage("home");
    }

    public void setPage(String name_of_page){
        SwingUtilities.invokeLater(() -> {
            setContentPane(  pages_map.get(name_of_page)  );
            revalidate();
            repaint();
        }); 
    }


    public boolean registerCustomer(String full_name, String email, String password) {
        return server_ref.addCustomer(UUID.randomUUID(), full_name, email, password);
    }

    // public boolean attemptToRegister(String username, String password){
    //     Customer result = server_ref.attemptToRegister(username, password);
    //     if(result != null){
    //         session.put("user", result);
    //         return true;
    //     }

    //     return false;
    // }

    public boolean loginUser(String email, String password) {
        return server_ref.loginUser(email, password);
    }


    // public void printAllevents(){
    //     ArrayList<Event> events =  server_ref.getAllEvents();


    //     for(Event e:events){
    //         System.out.printf("ID: %d, name: %s\n", e.getId(), e.getname());
    //     }
    // }

    public boolean loginAdmin(String username, String password, String idNumber, String phoneNumber) {
        return server_ref.loginAdmin(username, password, idNumber, phoneNumber);
    }

    public boolean addEvent(String name, LocalDate date, String time, String eventType, int capacity) {
        return server_ref.addEvent(UUID.randomUUID(), name, date, time, eventType, capacity);
    }

    public List<Event> getAvailableEvents() {
        return server_ref.getAvailableEvents();
    }

    public boolean createReservation(UUID customerId, UUID eventId, int ticketCount, double paymentAmount, boolean is_vip) {
        return server_ref.createReservation(customerId, eventId, ticketCount, paymentAmount, is_vip);
    }

    public int getAvailableSeats(UUID eventId, boolean is_vip) { //new
        return server_ref.getAvailableSeats(eventId, is_vip); //new
    }
    
    public double getTicketPrice(UUID eventId, boolean is_vip) { //new
        return server_ref.getTicketPrice(eventId, is_vip); //new
    }
    public int setEventRemainingCapacity(UUID selectedEventId,boolean is_vip,int ticketCount){
        return server_ref.setEventRemainingCapacity(selectedEventId,is_vip,ticketCount);
    }

    public boolean cancelReservation(UUID reservationId, UUID eventId, int ticketCount, boolean isVip) {
        return true; //server_ref.cancelReservation(reservationId, eventId, ticketCount, isVip);
    }

    public double calculateRefund(UUID reservationId) {
        return server_ref.calculateRefund(reservationId);
    }

    public boolean cancelEvent(UUID selectedEventId) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCurrentCustomerName() {
        return "name";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
