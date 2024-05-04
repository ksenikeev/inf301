package ru.itis.inf301.lab2_8.sync;

public class PrintUtil implements IPrintUtil {

    @Override
    public synchronized void print(String message) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < message.length(); ++i) {
            System.out.print(message.charAt(i));
        }
        System.out.println();
    }

    @Override
    public void printSyncBlock(String message) {
        System.out.println(Thread.currentThread().getName() + " in method");

        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " in block");
            for (int i = 0; i < message.length(); ++i) {
                System.out.print(message.charAt(i));
            }
            System.out.println();
        }
    }
}
