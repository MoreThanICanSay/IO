package com.socket.demo.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime()
        .availableProcessors(),maxPoolSize,120l, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void executor(Runnable task){
        System.out.println(Thread.currentThread().getName());
        executor.execute(task);
    }
}
