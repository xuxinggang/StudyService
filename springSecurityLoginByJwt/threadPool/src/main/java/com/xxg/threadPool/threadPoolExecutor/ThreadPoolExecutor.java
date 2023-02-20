package com.xxg.threadPool.threadPoolExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {
    public static class MyRunnable implements  Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("测试："+i);
            }
            try {
                System.out.println("睡眠前"+Thread.currentThread().getName());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
                System.out.println("睡眠后："+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
            java.util.concurrent.ThreadPoolExecutor threadPool = new java.util.concurrent.ThreadPoolExecutor(1, 2,
                    1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10),new java.util.concurrent.ThreadPoolExecutor.DiscardPolicy());
        MyRunnable myRunnable = new MyRunnable();
        threadPool.execute(myRunnable);

        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);

        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
    }
}
