package com.urise.webapp.lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();
    private final AtomicInteger atomicInteger = new AtomicInteger();
    public static final int THREADS_NUMBER = 10000;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }

        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " +
                Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        mainConcurrency.inc();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(counter);
//COUNTDOWN
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        mainConcurrency.inc();
                    }
                    latch.countDown();
                }
            });
            thread.start();
        }
        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter);
        //EXECUTION
        CountDownLatch latch2 = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch2.countDown();
                return 5;
            });
//            completionService.poll();
        }
        try {
            latch2.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        System.out.println(counter);
        System.out.println("Atomic: " + mainConcurrency.atomicInteger.get());


//        System.out.println("_____ Домашнее задание к уроку 11_____");
//        final Object res1 = 1;
//        final Object res2 = 2;
//        StartThreads(res1, res2);
//        StartThreads(res2, res1);
    }

    private static void StartThreads(Object res1, Object res2) {
        new Thread(() -> {
            System.out.println("Ожидаем " + res1);
            synchronized (res1) {
                System.out.println(res1 + "идет синхр блок");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Ожидаем " + res2);
                synchronized (res2) {
                    System.out.println(res2 + "идет синхр блок");
                }
                System.out.println(res2 + "закончен синхр блок");
            }
            System.out.println(res1 + "закончен синхр блок");
        }).start();
    }

    private void inc() {
//        synchronized (MainConcurrency.class) {
//        double a = Math.sin(13.);
        synchronized (this) {
            counter++;
//        }
//        }
        }
        atomicInteger.incrementAndGet();
    }
}
