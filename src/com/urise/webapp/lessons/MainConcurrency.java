package com.urise.webapp.lessons;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();

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

        System.out.println("_____ Домашнее задание к уроку 11_____");
        final Object res1 = 1;
        final Object res2 = 2;
        StartThreads(res1, res2);
        StartThreads(res2, res1);
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
    }
}
