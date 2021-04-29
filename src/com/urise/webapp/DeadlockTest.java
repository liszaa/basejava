package com.urise.webapp;

public class DeadlockTest {
    private static final int a = 8;
    private static final int b = 12;

    private static void doDeadlock(Integer a, Integer b) {
        log(a);
        synchronized (a) {
            try {
                log(a);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log(b);
            synchronized (b) {
                log(b);
                System.out.println(a - b);

            }
        }

    }

    private static void log(Integer x) {
        System.out.println(Thread.currentThread().getName() + " is waiting to " + x);
    }

    public static void main(String[] args) {

        // deadlock
        new Thread(() -> doDeadlock(a, b)).start();
        new Thread(() -> doDeadlock(b, a)).start();

    }

}
