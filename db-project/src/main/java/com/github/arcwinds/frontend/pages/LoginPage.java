package com.github.arcwinds.frontend.pages;

import javax.swing.JOptionPane;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;

public class LoginPage extends MainLayout {

    public LoginPage(MainFrame m) {
        super(m);
    }

    @Override
    protected void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton("Home Page"); //new
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        passwordField.setColumns(20);
        jScrollPane7.setViewportView(passwordField);

        loginButton.setBackground(new java.awt.Color(255, 51, 0));
        loginButton.setText("Login");
        loginButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); //new
        loginButton.setForeground(new java.awt.Color(255, 255, 255)); //new
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        homeButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16)); //new
        homeButton.setBackground(new java.awt.Color(102, 102, 255)); //new
        homeButton.setForeground(new java.awt.Color(255, 255, 255)); //new
        homeButton.setFocusPainted(false); //new
        homeButton.addActionListener(evt -> mf.setPage("home")); //new

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Username");
        jLabel1.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Password");
        jLabel2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14)); //new

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER) //new
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)) //new
                                .addContainerGap(500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10) //new
                        .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE) //new
                        .addContainerGap(366, Short.MAX_VALUE)
        );
        this.revalidate(); // Refresh layout
        this.repaint();
    }// </editor-fold>

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = jTextArea1.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ συμπληρώστε όλα τα πεδία!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
        boolean success = mf.loginUser(username, password);

        if (success) {

            JOptionPane.showMessageDialog(this, "Καλώς ήρθατε!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
            mf.setPage("dashboard");
        } else {

            int option = JOptionPane.showConfirmDialog(this,
                    "Λάθος στοιχεία! Θέλετε να κάνετε εγγραφή;",
                    "Σφάλμα Σύνδεσης",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                mf.setPage("register");
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton loginButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPasswordField passwordField;

}
