package ru.itis.inf301.lab2_4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class TestIterator {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");

        Iterator<String> iterator = list.iterator();

/*
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
*/

        System.out.println(iterator.next());
        System.out.println(iterator.next());

/*
        iterator.remove();

        System.out.println(iterator.next());
        iterator.remove();

        iterator = list.iterator();
*/
        list.add(4, "dd");

        System.out.println(iterator.next());
        System.out.println(iterator.next());


        iterator.forEachRemaining(new Printer());

    }

    public static class Printer implements Consumer<String> {

        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    }
}
