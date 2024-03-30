package ru.itis.inf301.lab2_4;

/**
 * Структура, хранящая однотипные элементы Integer
 * каждый элемент имеет позицию ( 0 .. )
 */
public interface List301<T> {
    void add(T e) ;

    void delete(int index) throws IndexOutOfBoundsException;

    T get(int index) throws IndexOutOfBoundsException;

    /** Количество хранимых элементов */
    int size();
}
