package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.common.Event;
import com.github.arcwinds.common.EventType;
import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminAddEvent extends MainLayout {

    public AdminAddEvent(MainFrame m) {
        super(m);
    }

    protected void initComponents() {
        // Δημιουργία Components
        EventType type;
        eventNameField = new javax.swing.JTextField();
        eventDateField = new javax.swing.JTextField();
        eventTimeField = new javax.swing.JTextField();
        eventTypeDropdown = new javax.swing.JComboBox<String>(getEventTypes()); //new
        eventCapacityField = new javax.swing.JTextField();
        addEventButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();

        // Ετικέτες για τα πεδία
        javax.swing.JLabel eventNameLabel = new javax.swing.JLabel("Event Name:");
        javax.swing.JLabel eventDateLabel = new javax.swing.JLabel("Event Date (YYYY-MM-DD):");
        javax.swing.JLabel eventTimeLabel = new javax.swing.JLabel("Event Time (HH:MM:SS):");
        javax.swing.JLabel eventTypeLabel = new javax.swing.JLabel("Event Type:");
        javax.swing.JLabel eventCapacityLabel = new javax.swing.JLabel("Capacity:");
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Admin Dashboard - Add Event");

        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        titleLabel.setForeground(new java.awt.Color(0, 102, 204));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        eventNameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventDateLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventTimeLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16)); //new
        eventTypeLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
        eventCapacityLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));

        eventNameField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventDateField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        eventTimeField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new
        eventTypeDropdown.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new
        eventCapacityField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));

        addEventButton.setText("Add Event");
        addEventButton.setBackground(new java.awt.Color(0, 153, 255));
        addEventButton.setForeground(new java.awt.Color(255, 255, 255));
        addEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        addEventButton.setFocusPainted(false);
        addEventButton.addActionListener(evt -> addEventButtonActionPerformed());

        eventDropdown = new JComboBox<>(); //new
        cancelEventButton = new JButton("Cancel Event"); //new
        cancelEventMessage = new JLabel(""); //new

        cancelEventButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); //new
        cancelEventButton.setBackground(new java.awt.Color(255, 102, 102)); //new
        cancelEventButton.setForeground(new java.awt.Color(255, 255, 255)); //new
        cancelEventButton.setFocusPainted(false); //new
        cancelEventButton.addActionListener(e -> cancelEvent()); //new

        cancelEventMessage.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new
        cancelEventMessage.setHorizontalAlignment(SwingConstants.CENTER); //new

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
                                        .addComponent(eventNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(eventDateField)
                                        .addComponent(eventTimeField) //new
                                        .addComponent(eventTypeDropdown) //new
                                        .addComponent(eventCapacityField)
                                        .addComponent(addEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eventDropdown, 0, 300, Short.MAX_VALUE) //new
                                        .addComponent(cancelEventButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE) //new
                                        .addComponent(cancelEventMessage, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE) //new
                                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addGap(20, 20, 20)
                                .addComponent(addEventButton)
                                .addGap(20, 20, 20)
                                .addComponent(eventDropdown, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE) //new
                                .addGap(20, 20, 20)
                                .addComponent(cancelEventButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE) //new
                                .addGap(10, 10, 10)
                                .addComponent(cancelEventMessage, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE) //new
                                .addGap(20, 20, 20)
                                .addGap(10, 10, 10)
                                .addComponent(logoutButton)
                                .addGap(20, 20, 20)
                                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private void addEventButtonActionPerformed() {
        String name = eventNameField.getText();
        String date = eventDateField.getText();
        String time = eventTimeField.getText();
        String eventType = eventTypeDropdown.getSelectedItem().toString(); //new
        String capacityText = eventCapacityField.getText();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateText = LocalDate.parse(date, dateFormatter);
        if (name.isEmpty() || time.isEmpty() || eventType.isEmpty() || capacityText.isEmpty()) {
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

    private String[] getEventTypes() { //new
        EventType[] eventTypes = EventType.values();
        String[] eventTypeStrings = new String[eventTypes.length];
        for (int i = 0; i < eventTypes.length; i++) {
            eventTypeStrings[i] = eventTypes[i].name();
        }
        return eventTypeStrings;
    }

    private void loadEvents() { //new
        eventDropdown.removeAllItems();
        List<Event> events = mf.getAvailableEvents();

        for (Event event : events) {
            eventDropdown.addItem(event.getName() + " (" + event.getDateTime() + ")");
        }

        eventDropdown.addActionListener(e -> {
            int selectedIndex = eventDropdown.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < events.size()) {
                selectedEventId = events.get(selectedIndex).getEventID();
            }
        });
    }

    private void cancelEvent() { //new
        if (selectedEventId == null) {
            cancelEventMessage.setText("Please select an event to cancel.");
            return;
        }

        boolean success = mf.cancelEvent(selectedEventId); // Κλήση στη συνάρτηση ακύρωσης εκδήλωσης στο MainFrame //new

        if (success) {
            cancelEventMessage.setText("Event cancelled successfully!");
            loadEvents(); // Ανανεώνει το dropdown
        } else {
            cancelEventMessage.setText("Failed to cancel the event.");
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
    private javax.swing.JLabel messageLabel;
    private javax.swing.JComboBox<String> eventTypeDropdown; //new

    private JComboBox<String> eventDropdown; //new
    private javax.swing.JButton cancelEventButton; //new
    private JLabel cancelEventMessage; //new
    private UUID selectedEventId; //new

}
