
package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.EventType;
import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import java.util.List;
import java.util.UUID;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class AdminAddEvent extends MainLayout {

    public AdminAddEvent(MainFrame m) {
        super(m);
    }

    protected void initComponents() {
        // Δημιουργία Components
        eventNameField = new javax.swing.JTextField();
        eventDateField = new javax.swing.JTextField();
        eventTimeField = new javax.swing.JTextField();
        eventTypeDropdown = new javax.swing.JComboBox<>(getEventTypes()); //new
        eventCapacityField = new javax.swing.JTextField();
        eventVipTicketField = new javax.swing.JTextField();
        eventNormalPriceField = new javax.swing.JTextField();
        eventVipPriceField = new javax.swing.JTextField();
        addEventButton = new javax.swing.JButton();
        backToHomeButton = new javax.swing.JButton();


        // Ετικέτες για τα πεδία
        javax.swing.JLabel eventNameLabel = new javax.swing.JLabel("Event Name:");
        javax.swing.JLabel eventDateLabel = new javax.swing.JLabel("Event Date (YYYY-MM-DD):");
        javax.swing.JLabel eventTimeLabel = new javax.swing.JLabel("Event Time (HH:MM:SS):");
        javax.swing.JLabel eventTypeLabel = new javax.swing.JLabel("Event Type:");
        javax.swing.JLabel eventCapacityLabel = new javax.swing.JLabel("Capacity:");
        
        javax.swing.JLabel vipTicketLabel = new javax.swing.JLabel("Initial VIP Tickets:");
        javax.swing.JLabel normalPriceLabel = new javax.swing.JLabel("Normal Price:");
        javax.swing.JLabel vipPriceLabel = new javax.swing.JLabel("VIP Price:");
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Admin Dashboard - Add Event");

        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        titleLabel.setForeground(new java.awt.Color(0, 102, 204));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        eventNameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventDateLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventTimeLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16)); //new
        eventTypeLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventCapacityLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        vipTicketLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        normalPriceLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        vipPriceLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));

        eventNameField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventDateField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventTimeField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new
        eventTypeDropdown.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new
        eventCapacityField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventVipTicketField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventNormalPriceField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventVipPriceField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));

        addEventButton.setText("Add Event");
        addEventButton.setBackground(new java.awt.Color(0, 153, 255));
        addEventButton.setForeground(new java.awt.Color(255, 255, 255));
        addEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        addEventButton.setFocusPainted(false);
        addEventButton.addActionListener(evt -> addEventButtonActionPerformed());

        backToHomeButton.setText("Back to home");
        backToHomeButton.setBackground(new java.awt.Color(255, 51, 51));
        backToHomeButton.setForeground(new java.awt.Color(255, 255, 255));
        backToHomeButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        backToHomeButton.setFocusPainted(false);
        backToHomeButton.addActionListener(evt -> { 
            mf.setPage("admindashboard");
        });

        setBackground(new java.awt.Color(230, 240, 255));

        // Διάταξη Components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eventNameLabel)
                                        .addComponent(eventDateLabel)
                                        .addComponent(eventTimeLabel) //new
                                        .addComponent(eventTypeLabel)
                                        .addComponent(eventCapacityLabel)
                                        .addComponent(normalPriceLabel)
                                        .addComponent(vipTicketLabel)
                                        .addComponent(vipPriceLabel)
                                        .addComponent(eventNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(eventDateField)
                                        .addComponent(eventTimeField) //new
                                        .addComponent(eventTypeDropdown) //new
                                        .addComponent(eventCapacityField)
                                        .addComponent(eventNormalPriceField)
                                        .addComponent(eventVipTicketField)
                                        .addComponent(eventVipPriceField)
                                        .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backToHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(titleLabel)
                                .addGap(20, 20, 20)
                                .addComponent(eventNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(eventDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(eventTimeLabel) //new
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE) //new
                                .addGap(10, 10, 10)
                                .addComponent(eventTypeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventTypeDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE) //new
                                .addGap(10, 10, 10)
                                .addComponent(eventCapacityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(10, 10, 10)
                                .addComponent(normalPriceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventNormalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(vipTicketLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventVipTicketField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(vipPriceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventVipPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(addEventButton)
                                .addGap(10, 10, 10)
                                .addComponent(backToHomeButton)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private void addEventButtonActionPerformed() {
        String name = eventNameField.getText();
        String date = eventDateField.getText();
        String time = eventTimeField.getText();
        String vipTickets = eventVipTicketField.getText();
        String vipPriceField = eventVipPriceField.getText();
        String normalPriceField = eventNormalPriceField.getText();
        Object eventType = eventTypeDropdown.getSelectedItem();
        String capacityText = eventCapacityField.getText();

        if (name.isEmpty() || date.isEmpty() || time.isEmpty() || eventType == null || capacityText.isEmpty() ||
                vipTickets.isEmpty() || vipPriceField.isEmpty() || normalPriceField.isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Παρακαλώ συμπληρώστε όλα τα πεδία!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int capacity = Integer.parseInt(capacityText);
        int vip = Integer.parseInt(vipTickets);
        int normalPrice = Integer.parseInt(normalPriceField);
        int vipPrice = Integer.parseInt(vipPriceField);

        if(capacity < vip){
            JOptionPane.showMessageDialog(this, "Initial total available tickets should be less than the capacity of the event!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean success = mf.addEvent(name, date, time, eventType.toString(), capacity, vip, normalPrice, vipPrice);
            if (success) {
                JOptionPane.showMessageDialog(this, "Evend added successfully!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                mf.setPage("admindashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Evend not added", "Αποτυχία", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "Capacity must be a valid number", "Αποτυχία", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] getEventTypes() { //new
        EventType[] eventTypes = EventType.values();
        String[] eventTypeStrings = new String[eventTypes.length];
        for (int i = 0; i < eventTypes.length; i++) {
            eventTypeStrings[i] = eventTypes[i].name();
        }
        return eventTypeStrings;
    }

    // Variables declaration
    private javax.swing.JTextField eventNameField;
    private javax.swing.JTextField eventDateField;
    private javax.swing.JTextField eventTimeField;
    private javax.swing.JTextField eventVipTicketField;
    private javax.swing.JTextField eventNormalPriceField;
    private javax.swing.JTextField eventVipPriceField;
    private javax.swing.JTextField eventCapacityField;
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JComboBox<String> eventTypeDropdown; //new
}
