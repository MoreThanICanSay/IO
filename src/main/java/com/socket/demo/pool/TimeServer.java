package com.socket.demo.pool;

import com.socket.demo.synchronousBlock.service.TimeServiceHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;
            // 创建线程池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);

            while (true){
                socket = server.accept();
                singleExecutor.executor(new TimeServiceHandler(socket));
            }
        } catch (Exception e) {
        } finally {

        }
    }
}
