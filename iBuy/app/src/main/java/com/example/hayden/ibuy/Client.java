package com.example.hayden.ibuy;


import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() {

    }
}
