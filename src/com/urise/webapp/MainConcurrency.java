package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static final Object LOCK = new Object();
    private final static Integer a = 8;
    private final static Integer b = 10;
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        final MainConcurrency mainConcurrency = new MainConcurrency();

        // deadlock
        Thread thread1 = new Thread(() -> transfer(a, b));
        thread1.start();

        Thread thread2 = new Thread(() -> transfer(b, a));
        thread2.start();


        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }


        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);
    }

    // deadlock
    private static void transfer(Integer a, Integer b) {
        synchronized (a) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(a - b);

            }
        }
    }

    private synchronized void inc() {
        counter++;
    }

}