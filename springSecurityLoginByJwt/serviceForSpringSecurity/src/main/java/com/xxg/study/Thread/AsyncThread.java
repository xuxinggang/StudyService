package com.xxg.study.Thread;

import com.xxg.study.domain.Member;
import com.xxg.study.mapper.MemberMapper;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 异步调用线程池
 */
@EnableAsync(proxyTargetClass=true)
@Configuration
public class AsyncThread {

    @Resource
    private MemberMapper memberMapper;



    //todo ：线程池的调用;线程池一般分位俩类 1、ThreadPoolExecutor 2、Executors 来创建线程池

//            1. Executors.newFixedThreadPool：创建⼀个固定⼤⼩的线程池，可控制并发的线程数，超出的线程会在队列中等待；
//            2. Executors.newCachedThreadPool：创建⼀个可缓存的线程池，若线程数超过处理所需，缓存⼀段时间后会回收，若线程数不够，则新建线程；
//            3. Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执⾏顺序；
//            4. Executors.newScheduledThreadPool：创建⼀个可以执⾏延迟任务的线程池；
//            5. Executors.newSingleThreadScheduledExecutor：创建⼀个单线程的可以执⾏延迟任务的线程池；
//            6. Executors.newWorkStealingPool：创建⼀个抢占式执⾏的线程池（任务执⾏顺序不确定）【JDK1.8 添加】。
//            7. ThreadPoolExecutor：最原始的创建线程池的⽅式，它包含了 7 个参数可供设置，后⾯会详细讲。

    /**
     * 固定线程池
     */
    void newFixedThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Member member = new Member();
        member=memberMapper.selectById(1);
        Member finalMember = member;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("固定线程池！！！！"+ finalMember);
                System.out.println(Thread.currentThread().getName()+" ");
                System.out.println(Thread.currentThread().getName()+" ");
            }
        },member);
    }

    /**
     * 有结果回调
     */
    void newFixedThreadPoolCallBack(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Member member = new Member();
        member=memberMapper.selectById(1);
        Member finalMember = member;
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        });
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓存线程池
     */
    @Test
    void newCachedThreadPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" ");
                }
            });
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" ");
            }
        });
    }
    /**
     * 单一线程池
     */
    void newSingleThreadExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Member member = new Member();
        member=memberMapper.selectById(1);
        Member finalMember = member;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("固定线程池！！！！"+ finalMember);
                System.out.println(Thread.currentThread().getName()+" ");
                System.out.println(Thread.currentThread().getName()+" ");
            }
        },member);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" ");
            }
        });
    }

    /**
     * 可延迟线程池队列
     */
    void newScheduledThreadPool(){
        ExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" ");
                System.out.println(Thread.currentThread().getName()+" ");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" ");
            }
        });

    }

    /**
     * 延时线程池
     */
   static void newScheduledThreadPoolDelay(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是延时执行线程池！！！！");
                executorService.shutdown();
            }
        },1,TimeUnit.MINUTES);

        System.out.println("我免费拉！！！！");
    }

    /**
     * 初始20秒后执行，然后以每四秒执行一次
     */
    static void newScheduledThreadPoolDelayAverage(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是平均延时执行线程池！！！！"+ LocalDateTime.now());
            }
        },2,0,TimeUnit.SECONDS);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("我是平均延时执行线程池！！！！"+ LocalDateTime.now());
//            }
//        },20,4, TimeUnit.SECONDS);

        System.out.println("我免费拉！！！！");
    }

    public static void main(String[] args) {
//        newScheduledThreadPoolDelay();
        newScheduledThreadPoolDelayAverage();
    }
    public static void mains(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        });
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Member member = new Member();
        member.setId(100000L);
        member.setBal(new BigDecimal("999999"));
        Member finalMember = member;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("固定线程池！！！！"+ finalMember);
                System.out.println(Thread.currentThread().getName()+" ");
                System.out.println(Thread.currentThread().getName()+" ");
            }
        },member);
        long before = System.currentTimeMillis();

        try {
            System.out.println();
            executorService.awaitTermination(10,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long late = System.currentTimeMillis();
        System.out.println(late-before);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" ");
            }
        });
    }
}
