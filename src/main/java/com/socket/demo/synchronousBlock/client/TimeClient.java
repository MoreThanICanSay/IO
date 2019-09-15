package com.socket.demo.synchronousBlock.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        int i = 0;
        while (i++ < 100) {
            try {
                socket = new Socket("127.0.0.1", port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Send order 2 service success ");

                String res = in.readLine();
                System.out.println("message is : " + res);
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
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
