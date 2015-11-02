package com.example.hayden.ibuy;

import java.net.Socket;

public class Connection {

    private static int PORT_NUMBER = 23657;
    private static String SERVER_IP= "127.0.0.1";

    public Connection() throws Exception {
        Socket socket = new Socket(SERVER_IP, PORT_NUMBER);
        Thread thread = new Thread(new Client(socket), "Connection to Server");
        thread.start();
    }
}
