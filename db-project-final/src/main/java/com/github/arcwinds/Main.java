package com.github.arcwinds;

import com.github.arcwinds.backend.Server;
import com.github.arcwinds.frontend.MainFrame;

public class Main {
    public static final boolean DEBUG_MODE = false;

    public static void main(String[] args) {
        Server server = new Server();

        MainFrame main = new MainFrame(server);
        main.setVisible(true);
        //kathe fora poy theloyme na epikoinwnisoume me tin basi tha prepei na ftiaxnoyme mia sunartisi ston server kai auti na epikoinwnei me tin basi
        server.getDb().closeConnection();
    }
}