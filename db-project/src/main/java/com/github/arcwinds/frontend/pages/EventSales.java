/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import javax.swing.JButton; // Import JButton
import javax.swing.JPanel;  // Import JPanel if needed (should be implicitly imported by inheriting from JPanel)
import java.awt.event.ActionListener;
/**
 *
 * @author 30694
 */
public class EventSales extends MainLayout {
    // Constructor
    public EventSales(MainFrame m) {
        super(m); // Pass the MainFrame instance to the parent class
    }

    @Override
    protected void initComponents() {
        // Example implementation of initComponents
        

        // Add your components here
        JButton eventButton = new JButton("Event Details");
       
        this.add(eventButton); // Add the button to the layout (JPanel)
    }
}
