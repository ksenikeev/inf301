package ru.itis.inf301.map;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        //objects = strings;
        objects.add(new Object());
        strings.get(0);


    }

    public void printList(List<?> lst) {
        for (Object o : lst) System.out.println(o);
    }

/*
    1. Разработать иерархическую структуру классов (корень - 2 ветки по 2 класса)
    2. Создать метод обрабатывающий любой список из классов из этой структуры (унаследованных от корня)
    3. Создать два метода обрабатывающих списки классов только из одной ветки
    4. Протестировать
*/
}
