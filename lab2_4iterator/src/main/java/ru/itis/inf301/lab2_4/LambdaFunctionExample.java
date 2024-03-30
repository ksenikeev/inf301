package ru.itis.inf301.lab2_4;

import java.util.function.Function;

public class LambdaFunctionExample {

    public static void main(String[] args) {

        Function<Double,Double> testFunc = Math::sqrt;//(a) -> Math.sqrt(a);
        Function<Double,Double> testFunc1 = new Function<Double, Double>() {
            @Override
            public Double apply(Double a) {
                return Math.sqrt(a);
            }
        };

        System.out.println(testFunc.apply(121d));

    }

    private static class TestFunction implements Function<Double,Double> {
        @Override
        public Double apply(Double param) {
            return Math.sqrt(param);
        }
    }
}
