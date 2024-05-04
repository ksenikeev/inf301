package ru.itis.inf301.lab2_8;

public class MainIntegral implements Summator {
    private double sum = 0;

    public synchronized void addSum(double partValue) {
        sum += partValue;
    }


}
