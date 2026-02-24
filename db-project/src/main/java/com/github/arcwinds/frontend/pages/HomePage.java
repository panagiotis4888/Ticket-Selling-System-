package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;

public class HomePage extends MainLayout{
    public HomePage(MainFrame m){
        super(m);
    }
    
    protected void initComponents() {

        registerButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        registerButton.setBackground(new java.awt.Color(255, 102, 102));
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(102, 255, 102));
        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        adminLoginButton = new javax.swing.JButton();
        adminLoginButton.setBackground(new java.awt.Color(255, 165, 0));
        adminLoginButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        adminLoginButton.setText("Admin Login");
        adminLoginButton.addActionListener(evt -> {
            mf.setPage("adminPage"); // Μεταφορά στη σελίδα AdminLogin
        });

     

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
this.setLayout(layout);

layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER) // Center horizontally
        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(adminLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
);

layout.setVerticalGroup(
    layout.createSequentialGroup()
        .addGap(100) // Top gap to center the buttons vertically on the page
        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(20) // Gap between buttons
        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(20) // Gap between buttons
        .addComponent(adminLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(100) // Bottom gap to center the buttons vertically
);

    }// </editor-fold>                        

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        mf.setPage("register");
    }                                        

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        mf.setPage("login");
    }                                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton registerButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton adminLoginButton;
    // End of variables declaration                 

}
