package ru.itis.inf301.lab2_5.funcinterfaces;

import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {
        Predicate predicate = new TestPredicate();

        System.out.println(predicate.test(201));
    }


    private static class TestPredicate implements Predicate<Integer> {

        @Override
        public boolean test(Integer integer) {
            return integer % 2 == 0;
        }

        @Override
        public Predicate<Integer> negate() {
            return (new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    return !(integer % 2 == 0);
                }
            });
        }
    }
}
