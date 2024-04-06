package ru.itis.inf301.lab2_4;

import java.util.ArrayList;
import java.util.List;

public class MainFor {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }

        list.forEach(MainFor::print);

        List<String> lst;

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
