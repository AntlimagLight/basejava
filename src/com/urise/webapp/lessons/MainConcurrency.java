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
