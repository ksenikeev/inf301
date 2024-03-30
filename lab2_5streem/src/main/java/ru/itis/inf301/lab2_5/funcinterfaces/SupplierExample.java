package ru.itis.inf301.lab2_5.funcinterfaces;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SupplierExample {

    public static String s = "Hello!";

    public static void accept(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    public static void main(String[] args) {
        accept(new MySupplier());
        accept(new Supplier<String>() {
            public String get() {
                return s;
            }
        });

        accept(() -> {return s;});
        accept(() -> s);

    }

    static class MySupplier implements Supplier<String> {
        public String get() {
            return s;
        }
    }

}
