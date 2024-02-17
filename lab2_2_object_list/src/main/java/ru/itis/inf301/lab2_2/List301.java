package ru.itis.inf301.lab2_2;

/**
 * Структура, хранящая однотипные элементы Integer
 * каждый элемент имеет позицию ( 0 .. )
 */
public interface List301 <T> {
    void add(T e);

    void delete(int index);

    T pop();

    T get(int index);

    /** Количество хранимых элементов */
    int size();
}
