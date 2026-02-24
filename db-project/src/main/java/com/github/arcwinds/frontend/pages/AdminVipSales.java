/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import javax.swing.JButton;

/**
 *
 * @author 30694
 */
public class AdminVipSales extends MainLayout {
    // Constructor
    public AdminVipSales(MainFrame m) {
        super(m); // Pass the MainFrame instance to the parent class
    }

    @Override
    protected void initComponents() {
        // Example implementation of initComponents
        

        // Add your components here
        JButton eventButton = new JButton("Event 5 Details");
       
        this.add(eventButton); // Add the button to the layout (JPanel)
    }
}
