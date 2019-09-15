package com.socket.demo.synchronousBlock.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServiceHandler implements Runnable {

    private Socket socket;

    public TimeServiceHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream()
            ));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " > The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()
                ).toString() : " BAD ORDER";
                out.println(currentTime);
                System.out.println("currentTime is : " + currentTime);
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (this.socket != null) {
                this.socket = null;
            }
        }
    }
}
