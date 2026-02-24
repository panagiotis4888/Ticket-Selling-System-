/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.arcwinds.frontend.pages;

import com.github.arcwinds.common.Event;
import com.github.arcwinds.frontend.MainFrame;
import com.github.arcwinds.frontend.layouts.MainLayout;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mixalis
 */
public class AdminCancelEvent extends MainLayout {

    public AdminCancelEvent(MainFrame m) {
        super(m);
        updateEventList();
    }
    
    
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    protected void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        eventStringList = new javax.swing.JList<>();
        cancelEventButton = new javax.swing.JButton();
        backToHomeButton = new javax.swing.JButton();
           
        

        cancelEventButton.setText("Cancel Selected Event");
        cancelEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEventButtonActionPerformed(evt);
            }
        });

        backToHomeButton.setText("Back to home");
        backToHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToHomeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backToHomeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 169, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelEventButton)
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backToHomeButton)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(cancelEventButton)
                .addContainerGap(123, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void backToHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        mf.setPage("admindashboard");
    }                                                

    private void cancelEventButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        int index = eventStringList.getSelectedIndex();
        
        Event selectedEvent = eventList.get(index);
        double refundAmount = mf.cancelEvent(selectedEvent.getEventID());
        
        JOptionPane.showMessageDialog(this, "Event cancelled, total money refunded: " + refundAmount, "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
        
        updateEventList();
    }                                                 
    
    private void updateEventList(){
        eventList = mf.getAvailableEvents();
        String[] stringss = new String[eventList.size()];
        for(int i = 0; i < stringss.length; i++){
            stringss[i] = eventList.get(i).getName();
        }
        eventStringList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = stringss;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(eventStringList);
        
        
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton backToHomeButton;
    private javax.swing.JButton cancelEventButton;
    private javax.swing.JList<String> eventStringList;
    private javax.swing.JScrollPane jScrollPane1;
    private List<Event> eventList;
    // End of variables declaration    
    
}
