package ru.itis.inf301.lab2_5.funcinterfaces;

import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {

        Function<Double,Double> testFunc = new TestFunction();

        System.out.println(testFunc.apply(121d));

        testFunc = a -> a*a;

        System.out.println(testFunc.apply(11d));

    }

    private static class TestFunction implements Function<Double,Double> {
        @Override
        public Double apply(Double param) {
            return Math.sqrt(param);
        }
    }
}
