package ru.itis.inf301.lab2_8;

public class PrintTaskThread extends Thread {

    private String data;

    public PrintTaskThread(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < data.length(); ++i) {
            System.out.print(data.charAt(i));
        }
        System.out.println();
    }
}
