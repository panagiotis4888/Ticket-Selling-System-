package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import javax.swing.JComboBox;

public class AdminLoginPage extends MainLayout {

    public AdminLoginPage(MainFrame m) {
        super(m);
    }

    protected void initComponents() {
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        homeButton = new javax.swing.JButton("Home Page");
        loginButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(220, 240, 255));

        titleLabel.setText("Admin Login");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        usernameLabel.setText("Username:");
        passwordLabel.setText("Password:");

        loginButton.setText("Admin Login");
        loginButton.setBackground(new java.awt.Color(0, 102, 204));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        loginButton.addActionListener(evt -> loginButtonActionPerformed());

        homeButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); //new
        homeButton.setBackground(new java.awt.Color(102, 102, 255)); //new
        homeButton.setForeground(new java.awt.Color(255, 255, 255)); //new
        homeButton.setFocusPainted(false); //new
        homeButton.addActionListener(evt -> mf.setPage("home")); //new

        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setForeground(new java.awt.Color(255, 0, 0));

        // Layout components
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE) // Προσθήκη τίτλου
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usernameLabel)
                                                        .addComponent(passwordLabel))
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE) //new
                                        .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(titleLabel) // Προσθήκη τίτλου
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameLabel)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                )
                                .addGap(20, 20, 20)
                                .addComponent(loginButton)
                                .addGap(10, 10, 10)
                                .addComponent(homeButton) //new
                                .addGap(10, 10, 10)
                                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private void loginButtonActionPerformed() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Συμπληρώστε όλα τα πεδία!");
            return;
        }

        boolean isAdmin = mf.loginAdmin(username, password);
        if (isAdmin) {
            mf.setPage("admindashboard"); // Μεταφορά στη σελίδα διαχείρισης
        } else {
            messageLabel.setText("Λάθος στοιχεία! Δοκιμάστε ξανά.");
        }
    }

    // Components
    private javax.swing.JTextField usernameField;
    private javax.swing.JPasswordField passwordField;

    private javax.swing.JButton loginButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel passwordLabel;

    private JComboBox<String> eventDropdown; //new
}
