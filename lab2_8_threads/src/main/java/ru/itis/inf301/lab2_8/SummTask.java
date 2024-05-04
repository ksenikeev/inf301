package ru.itis.inf301.lab2_8;

public class SummTask {

    private static int N = 1000;

    private double a;
    private double b;

    private Summator summator;

    private double sum = 0;

    public double getSum() {
        return sum;
    }

    public SummTask(double a, double b, Summator summator) {
        this.a = a;
        this.b = b;
        this.summator = summator;
    }

    private double func(double x) {
        return x;
    }

    public void run() {
        //sum += ;

        summator.addSum(sum);
    }
}
