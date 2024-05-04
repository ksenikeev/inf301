package ru.itis.inf301.lab2_8.sync;

public class PrintTask implements Runnable {

    private String data;
    private IPrintUtil printUtil;

    public PrintTask(IPrintUtil printUtil, String data) {
        this.data = data;
        this.printUtil = printUtil;
    }

    @Override
    public void run() {
        //printUtil.print(data);
        printUtil.printSyncBlock(data);
    }
}
