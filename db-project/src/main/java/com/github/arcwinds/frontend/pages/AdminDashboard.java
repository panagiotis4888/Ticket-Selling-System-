package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDashboard extends MainLayout {


    public AdminDashboard(MainFrame m){
        super(m);
    }

    protected void initComponents() {
        // Δημιουργία Components
        messageLabel = new javax.swing.JLabel();
        addEventButton = new javax.swing.JButton();
        EventSalesButton = new javax.swing.JButton();
        MostPopularButton = new javax.swing.JButton();
        TimeSalesButton= new javax.swing.JButton();
        TimeReserveButton= new javax.swing.JButton();
        VipSalesButton= new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();
   


        addEventButton.setText("Add Event");
        addEventButton.setBackground(new java.awt.Color(0, 153, 255)); 
        addEventButton.setForeground(new java.awt.Color(255, 255, 255)); 
        addEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
        addEventButton.setFocusPainted(false); 
        addEventButton.addActionListener(evt -> {
            try {
                addEventButtonActionPerformed();
            } catch (ParseException ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addEventButton.addActionListener(evt -> mf.setPage("addevent"));
        
        EventSalesButton.setText("Events Sales");
        EventSalesButton.setBackground(new java.awt.Color(255, 255, 0));
        EventSalesButton.setForeground(new java.awt.Color(255, 255, 255)); 
        EventSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
        EventSalesButton.setFocusPainted(false); 
        EventSalesButton.addActionListener(evt -> mf.setPage("eventsales"));
        
        MostPopularButton.setText("Most Popular Event");
        MostPopularButton.setBackground(new java.awt.Color(0, 255, 0));
        MostPopularButton.setForeground(new java.awt.Color(255, 255, 255)); 
        MostPopularButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
        MostPopularButton.setFocusPainted(false); 
        MostPopularButton.addActionListener(evt -> mf.setPage("mostpopular"));
        
        TimeSalesButton.setText("Time Dependend Sales");
        TimeSalesButton.setBackground(new java.awt.Color(128, 0, 128));
        TimeSalesButton.setForeground(new java.awt.Color(255, 255, 255)); 
        TimeSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
        TimeSalesButton.setFocusPainted(false); 
        TimeSalesButton.addActionListener(evt -> mf.setPage("timesales"));
        
        TimeReserveButton.setText("Time Dependend Reservations");
        TimeReserveButton.setBackground(new java.awt.Color(255, 182, 193));
        TimeReserveButton.setForeground(new java.awt.Color(255, 255, 255)); 
        TimeReserveButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
       TimeReserveButton.setFocusPainted(false); 
        TimeReserveButton.addActionListener(evt -> mf.setPage("timereserve"));
        
        VipSalesButton.setText("Vip Sales");
        VipSalesButton.setBackground(new java.awt.Color(255, 165, 0));
        VipSalesButton.setForeground(new java.awt.Color(255, 255, 255)); 
        VipSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
       VipSalesButton.setFocusPainted(false); 
        VipSalesButton.addActionListener(evt -> mf.setPage("vipsales"));
        
        MenuButton.setText("Back");
        MenuButton.setBackground(new java.awt.Color(255, 165, 0));
        MenuButton.setForeground(new java.awt.Color(255, 255, 255)); 
        MenuButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); 
        MenuButton.setFocusPainted(false); 
        MenuButton.addActionListener(evt -> mf.setPage("dashboard"));
        
        logoutButton.setText("Log Out");
        logoutButton.setBackground(new java.awt.Color(255, 51, 51)); 
        logoutButton.setForeground(new java.awt.Color(255, 255, 255)); 
        logoutButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        logoutButton.setFocusPainted(false); 
        logoutButton.addActionListener(evt -> mf.setPage("home"));

        messageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); 
        messageLabel.setForeground(new java.awt.Color(255, 0, 0)); 
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        
        setBackground(new java.awt.Color(230, 240, 255));


        // Διάταξη Components

        
        
        
        
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
this.setLayout(layout);

layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Outer ParallelGroup
        .addGroup(layout.createSequentialGroup() // Outer SequentialGroup
            .addGap(50, 50, 50) // Left margin
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Inner ParallelGroup
                .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE) // Explicit width for the buttons
                .addComponent(EventSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MostPopularButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeReserveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VipSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(50, Short.MAX_VALUE)) // Right margin
);

                
                
                
                
                
      layout.setVerticalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Outer ParallelGroup
        .addGroup(layout.createSequentialGroup() // Outer SequentialGroup
            .addGap(20, 20, 20) // Top margin
            .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE) // Explicit height for each button
            .addGap(20, 20, 20) // Space between buttons
            .addComponent(EventSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(MostPopularButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(TimeSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(TimeReserveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
             .addComponent(VipSalesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(50, Short.MAX_VALUE)) // Bottom margin
);
    }

    private void addEventButtonActionPerformed() throws ParseException {
        String name = eventNameField.getText();
        String date = eventDateField.getText();
        String time = eventTimeField.getText();
        String eventType = eventTypeField.getText();
        String capacityText = eventCapacityField.getText();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateText = LocalDate.parse(date, dateFormatter);
        // Έλεγχος αν τα πεδία είναι κενά
        if (name.isEmpty() || date.isEmpty() || time.isEmpty() || eventType.isEmpty() || capacityText.isEmpty()) {
            messageLabel.setText("Please fill in all fields!");
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            boolean success = mf.addEvent(name, dateText, time, eventType, capacity);
            if (success) {
                messageLabel.setText("Event added successfully!");
            } else {
                messageLabel.setText("Failed to add event.");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Capacity must be a valid number.");
        }
    }

    // Variables declaration
    private javax.swing.JTextField eventNameField;
    private javax.swing.JTextField eventDateField;
    private javax.swing.JTextField eventTimeField;
    private javax.swing.JTextField eventTypeField;
    private javax.swing.JTextField eventCapacityField;
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton EventSalesButton;
    private javax.swing.JButton MostPopularButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton TimeSalesButton;
    private javax.swing.JButton TimeReserveButton;
    private javax.swing.JButton VipSalesButton;
    private javax.swing.JButton MenuButton;
}
