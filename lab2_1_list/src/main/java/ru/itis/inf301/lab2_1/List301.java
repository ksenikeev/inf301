package ru.itis.inf301.lab2_1;

/**
 * Структура, хранящая однотипные элементы Integer
 * каждый элемент имеет позицию ( 0 .. )
 */
public interface List301 {
    void add(Integer e) throws EmptyElementException;

    void delete(int index) throws IndexOutOfBoundsException;

    Integer pop();

    Integer get(int index) throws IndexOutOfBoundsException;

    /** Количество хранимых элементов */
    int size();
}
