package ru.itis.inf301.lab2_8;

import ru.itis.inf301.lab2_8.sync.IPrintUtil;

public class PrintTaskOld implements Runnable {

    private String data;
    private IPrintUtil printUtil;

    public PrintTaskOld(IPrintUtil printUtil, String data) {
        this.data = data;
        this.printUtil = printUtil;
    }

    @Override
    public void run() {
        printUtil.print(data);
    }
}
