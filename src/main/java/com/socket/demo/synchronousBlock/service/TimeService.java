package com.socket.demo.synchronousBlock.service;

import java.net.ServerSocket;
import java.net.Socket;

public class TimeService {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);

            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServiceHandler(socket)).start();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
