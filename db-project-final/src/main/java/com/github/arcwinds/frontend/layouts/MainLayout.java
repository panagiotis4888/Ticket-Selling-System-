package com.github.arcwinds.frontend.layouts;

import javax.swing.JPanel;

import com.github.arcwinds.frontend.MainFrame;

public abstract class MainLayout extends JPanel{
    protected MainFrame mf;

    public MainLayout(MainFrame m){
        mf = m;

        initComponents();
    }

    protected abstract void initComponents(); 
}
