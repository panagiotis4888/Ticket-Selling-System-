package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.Reservation;
import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AdminDashboard extends MainLayout {

    public AdminDashboard(MainFrame m) {
        super(m);
    }

    protected void initComponents() {
        // Δημιουργία Components
        messageLabel = new javax.swing.JLabel();
        addEventButton = new javax.swing.JButton();
        cancelEventButton = new javax.swing.JButton();
        EventSalesButton = new javax.swing.JButton();
        MostPopularButton = new javax.swing.JButton();
        TimeSalesButton = new javax.swing.JButton();
        TimeReserveButton = new javax.swing.JButton();
        VipSalesButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();

        addEventButton.setText("Add Event");
        addEventButton.setBackground(new java.awt.Color(0, 153, 255));
        addEventButton.setForeground(new java.awt.Color(255, 255, 255));
        addEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        addEventButton.setFocusPainted(false);
        addEventButton.addActionListener(evt -> mf.setPage("addevent"));

        cancelEventButton.setText("Cancel Event");
        cancelEventButton.setBackground(new java.awt.Color(0, 153, 255));
        cancelEventButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        cancelEventButton.setFocusPainted(false);
        cancelEventButton.addActionListener(evt -> mf.setPage("cancelevent"));

        EventSalesButton.setText("Events Sales");
        EventSalesButton.setBackground(new java.awt.Color(255, 255, 0));
        EventSalesButton.setForeground(new java.awt.Color(255, 255, 255));
        EventSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        EventSalesButton.setFocusPainted(false);
        EventSalesButton.addActionListener(evt -> mf.setPage("calculatesales"));

        MostPopularButton.setText("Most Popular Event");
        MostPopularButton.setBackground(new java.awt.Color(0, 255, 0));
        MostPopularButton.setForeground(new java.awt.Color(255, 255, 255));
        MostPopularButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        MostPopularButton.setFocusPainted(false);
        MostPopularButton.addActionListener(evt
                -> {
            Event e = mf.getMostPopularEvent();

            JOptionPane.showMessageDialog(this, "Most popular event: %s".formatted(e.getName()), "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);

        }
        );

        TimeSalesButton.setText("Time Dependend Sales");
        TimeSalesButton.setBackground(new java.awt.Color(128, 0, 128));
        TimeSalesButton.setForeground(new java.awt.Color(255, 255, 255));
        TimeSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        TimeSalesButton.setFocusPainted(false);
        TimeSalesButton.addActionListener(evt -> {
            LocalDate d1 = LocalDate.parse(JOptionPane.showInputDialog("Start date (YYYY-MM-DD)"));
            LocalDate d2 = LocalDate.parse(JOptionPane.showInputDialog("End date (YYYY-MM-DD)"));

            Event e = mf.getEventWithMostIncomeInDateRange(d1, d2);
            if (e == null) {
                JOptionPane.showMessageDialog(this, "No event found");
                return;
            }
            JOptionPane.showMessageDialog(this, "Event name: " + e.getName());

        });

        TimeReserveButton.setText("Time Dependend Reservations");
        TimeReserveButton.setBackground(new java.awt.Color(255, 182, 193));
        TimeReserveButton.setForeground(new java.awt.Color(255, 255, 255));
        TimeReserveButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        TimeReserveButton.setFocusPainted(false);
        TimeReserveButton.addActionListener(evt -> {
            LocalDate d1 = LocalDate.parse(JOptionPane.showInputDialog("Start date (YYYY-MM-DD)"));
            LocalDate d2 = LocalDate.parse(JOptionPane.showInputDialog("End date (YYYY-MM-DD)"));

            ArrayList<Reservation> l = mf.getReservationsByDateRange(d1, d2);
            if (l.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No reservations found");
                return;
            }
            
            String buf = "";
            
            for(Reservation r : l){
                buf += String.format("Reservation id: %s, customer id: %s, event id: %s, payment amount: %f, reservation date: %s, tickets:%d\n",
                        r.getReservationID().toString(), r.getCustomerID().toString(), r.getEventID().toString(), r.getPaymentAmount(), r.getReservationDate().toString(), r.getTicketCount());
            }
            
            JOptionPane.showMessageDialog(this, buf);

        });

        VipSalesButton.setText("Vip Sales");
        VipSalesButton.setBackground(new java.awt.Color(255, 165, 0));
        VipSalesButton.setForeground(new java.awt.Color(255, 255, 255));
        VipSalesButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        VipSalesButton.setFocusPainted(false);
        VipSalesButton.addActionListener(evt -> {
             JOptionPane.showMessageDialog(this, mf.getTotalVipSales());
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Outer ParallelGroup
                        .addGroup(layout.createSequentialGroup() // Outer SequentialGroup
                                .addGap(50, 50, 50) // Left margin
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Inner ParallelGroup
                                        .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE) // Explicit width for the buttons
                                        .addComponent(cancelEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(cancelEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE) // Explicit height for each button
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

    // Variables declaration
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton cancelEventButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton EventSalesButton;
    private javax.swing.JButton MostPopularButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton TimeSalesButton;
    private javax.swing.JButton TimeReserveButton;
    private javax.swing.JButton VipSalesButton;
    private javax.swing.JButton MenuButton;
}
