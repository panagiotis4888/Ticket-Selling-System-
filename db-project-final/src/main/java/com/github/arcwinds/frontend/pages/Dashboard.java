package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.common.Customer;
import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import com.github.arcwinds.common.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

//sto Dashboard kathe fora poy pataw ena event prepei na deixnei poses diathesimes theseis exei gia VIP kai gia aplous , episis prepei na deixnei kathe fora poy allazw reservation kai ticket count
//na deixnei ston pelati to kostos tou reservation poy tha upologizoume mesw tis sunartisis tou server
public class Dashboard extends MainLayout {

    public Dashboard(MainFrame m) {
        super(m);
        loadEvents();
        updateTags();
    }

    @Override
    protected void initComponents() {
        // Τίτλος σελίδας
        JLabel titleLabel = new JLabel("Customer Reservation Page");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new java.awt.Color(0, 102, 204));

        // Εμφάνιση ονόματος πελάτη
        JLabel customerInfoLabel = new JLabel("Customer: " + mf.getCurrentCustomerName()); //new
        customerInfoLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16)); //new
        customerInfoLabel.setForeground(new java.awt.Color(0, 102, 204)); //new

        // Επιλογή εκδήλωσης
        eventDropdown = new JComboBox<>();
        JLabel eventDropdownLabel = new JLabel("Select Event:");
        eventDropdownLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventDropdownLabel.setForeground(new java.awt.Color(50, 50, 50));

        // Εισαγωγή αριθμού εισιτηρίων
        JLabel ticketCountLabel = new JLabel("Tickets:");
        ticketCountLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        ticketCountLabel.setForeground(new java.awt.Color(50, 50, 50));
        ticketCountField = new JTextField();
        ticketCountField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        ticketCountField.setPreferredSize(new java.awt.Dimension(100, 30));

        // Κουμπί κράτησης
        JButton reserveButton = new JButton("Reserve");
        reserveButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        reserveButton.setBackground(new java.awt.Color(0, 153, 255));
        reserveButton.setForeground(new java.awt.Color(255, 255, 255));
        reserveButton.setFocusPainted(false);
        reserveButton.addActionListener(e -> reserveTickets()); //new

        // Κουμπί ακύρωσης κράτησης
        cancelReservationButton = new JButton("Cancel Reservation"); //new

        cancelReservationButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); //new
        cancelReservationButton.setBackground(new java.awt.Color(255, 102, 102)); //new
        cancelReservationButton.setForeground(new java.awt.Color(255, 255, 255)); //new
        cancelReservationButton.setFocusPainted(false); //new

        cancelReservationButton.addActionListener(e -> cancelReservationAction()); //new

   
        // Κουμπί επιστροφής
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        backButton.setBackground(new java.awt.Color(255, 51, 51));
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mf.setPage("home"));
        
        // vip κουμπι
        vip_toggle_button = new JButton("VIP: No");
        
        // Μηνύματα
        messageLabel = new JLabel("");
        messageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        messageLabel.setForeground(new java.awt.Color(255, 0, 0));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Ετικέτες για διαθεσιμότητα και τιμή
        availableSeatsLabel = new JLabel("Available Seats: ");
        availableSeatsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        availableSeatsLabel.setForeground(new java.awt.Color(50, 50, 50));

        ticketPriceLabel = new JLabel("Ticket Price: ");
        ticketPriceLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        ticketPriceLabel.setForeground(new java.awt.Color(50, 50, 50));

        // Ρύθμιση φόντου
        setBackground(new java.awt.Color(230, 240, 255));

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(customerInfoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) //new
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(eventDropdownLabel)
                                        .addComponent(eventDropdown, 0, 300, Short.MAX_VALUE)
                                        .addComponent(ticketCountLabel)
                                        .addComponent(ticketCountField)
                                        .addComponent(reserveButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelReservationButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE) //new
                                        .addComponent(vip_toggle_button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE) 
                                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(messageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(availableSeatsLabel)
                                        .addComponent(ticketPriceLabel))
                                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(titleLabel)
                        .addGap(10, 10, 10) // Space between title and customer info
                        .addComponent(customerInfoLabel) //new
                        .addGap(20, 20, 20)
                        .addComponent(eventDropdownLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventDropdown, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(ticketCountLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticketCountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(reserveButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cancelReservationButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE) //new
                        .addGap(10, 10, 10)
                        .addComponent(vip_toggle_button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE) //new
                        .addGap(10, 10, 10)
                        .addGap(10, 10, 10)
                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(availableSeatsLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticketPriceLabel)
        );
        
        vip_toggle_button.addActionListener(evt -> {
            if(vip_toggle){
                vip_toggle_button.setText("VIP: No");
            } else{
                vip_toggle_button.setText("VIP: Yes");
            }
            
            vip_toggle = !vip_toggle;
            
            updateTags();
        });
        this.revalidate(); // Refresh layout
        this.repaint(); // Redraw panel
    }

    public void loadEvents() {
        eventDropdown.removeAllItems();
        events = mf.getAvailableEvents();

        for (Event event : events) {
            eventDropdown.addItem(event.getName() + " (" + event.getDateTime() + ")");
        }

        eventDropdown.addActionListener(e -> {
            updateTags();
        });
    }

    private void reserveTickets() {
        try {
            int ticketCount = Integer.parseInt(ticketCountField.getText());
            if (ticketCount <= 0) {
                messageLabel.setText("Enter a valid ticket count!");
                return;
            }

            Customer currentCustomer = (Customer) mf.session.get("user");
            if (currentCustomer == null) { //new
                messageLabel.setText("No customer logged in!"); //new
                return; //new
            }

            UUID customerId = currentCustomer.getCustomerId(); //new

            double ticketPrice = mf.getTicketPrice(selectedEventId, vip_toggle);

            boolean success = mf.createReservation(customerId, selectedEventId, ticketCount, vip_toggle);

            if (success) {
                messageLabel.setText("Reservation successful!");
                loadEvents();
            } else {
                messageLabel.setText("Failed to reserve tickets.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid ticket count!");
        }
    }

    private void cancelReservationAction() {
        // cancel all reservations and print total refund amount
        double refundAmount = mf.cancelReservations(events.get(eventDropdown.getSelectedIndex()).getEventID());
        if(refundAmount == 0){
            messageLabel.setText("No reservations found");
            return;
        }
        messageLabel.setText("Refunds successful! You got back %.2f€.".formatted(refundAmount));
        updateTags();
    }

    private void updateTags() {
        int selectedIndex = eventDropdown.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < events.size()) {
            selectedEventId = events.get(selectedIndex).getEventID();

            int availableSeats = mf.getAvailableSeats(selectedEventId, vip_toggle);
            double ticketPrice = mf.getTicketPrice(selectedEventId, vip_toggle);
            
            availableSeatsLabel.setText("Available Seats: " + availableSeats);
            ticketPriceLabel.setText("Ticket Price: €" + ticketPrice);
        }
    }
    private JComboBox<String> eventDropdown;
    private JTextField ticketCountField;
    private JLabel messageLabel;
    private UUID selectedEventId;
    private JLabel availableSeatsLabel; // Διαθέσιμες θέσεις
    private JLabel ticketPriceLabel;    // Τιμή εισιτηρίου
    private JButton cancelReservationButton; // Νέο κουμπί
    private JButton vip_toggle_button;
    private boolean vip_toggle = false;
    private List<Event> events ;
}
