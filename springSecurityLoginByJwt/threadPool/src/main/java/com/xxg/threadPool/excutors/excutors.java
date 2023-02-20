package com.xxg.threadPool.excutors;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.*;

public class excutors {

    public static void singleThreadPool(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    System.out.println(i++);
                }
                System.out.println(Thread.currentThread().getName());
            }

        });
    }
    public static void threadPool(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 20,
                1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10),new ThreadPoolExecutor.DiscardPolicy());
        AbstractExecutorService threadPoolExecutor = new ThreadPoolExecutor(5, 20,
                1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.DiscardPolicy());
        threadPool.allowCoreThreadTimeOut(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < 9; i++) {
                    if (i >= 1000) {
                        threadPool.shutdown();
                    } else
                        System.out.println(Thread.currentThread().getName() + i);
                }
                try {
                    System.out.println(Thread.currentThread().getName() + "线程睡眠前");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "线程睡眠后：");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    public static void main(String[] args) throws TimeoutException {
        System.out.println(Thread.currentThread().getName());
//        singleThreadPool();
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
        }
        threadPool();

    }
}
