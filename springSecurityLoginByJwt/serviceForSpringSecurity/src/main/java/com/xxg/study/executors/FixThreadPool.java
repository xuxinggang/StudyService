package com.xxg.study.executors;


import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FixThreadPool {

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        //可以回去有返回值的数据
//        threadPool.submit(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                //一般是进行具体业务操作
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//        threadPool.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                    //一般是进行具体业务操作
//                    System.out.println(Thread.currentThread().getName());
//            }
//        });
//        Future<Integer> submit = threadPool.submit(new Callable<Integer>() {
//            int i = 1;
//
//            @Override
//            public Integer call() throws Exception {
//                for (; i < 50; i++) {
//                    i = i * i;
//                }
//                return i;
//            }
//        });
//        System.out.println("固定线程池返回结果："+submit.get());
//    }
public static void main(String[] args) {
    ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(@NotNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            //设置线程的命名规则
            thread.setName("我的线程" + runnable.hashCode());
            //设置线程的优先级
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        }
    };
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5, threadFactory);
    System.out.println("线程开始时间为："+LocalDateTime.now());
    ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
        @Override
        public void run() {
            System.out.println(LocalDateTime.now() + ":我的延时线程池启动了:" + Thread.currentThread().getName());
        }
    }, TimeUnit.SECONDS.toSeconds(3), TimeUnit.SECONDS);
}
}
