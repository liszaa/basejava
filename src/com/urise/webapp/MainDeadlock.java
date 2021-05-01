package com.urise.webapp;

public class MainDeadlock {
    private static final int a = 8;
    private static final int b = 12;

    public static void main(String[] args) {
        // deadlock
        new Thread(() -> doDeadlock(a, b)).start();
        new Thread(() -> doDeadlock(b, a)).start();
    }

    private static void doDeadlock(Integer a, Integer b) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " is waiting " + a);
        synchronized (a) {
            try {
                System.out.println(threadName + " is holding " + a);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " is waiting " + b);
            synchronized (b) {
                System.out.println(threadName + " is holding " + b);
                System.out.println(a - b);

            }
        }
    }
}
