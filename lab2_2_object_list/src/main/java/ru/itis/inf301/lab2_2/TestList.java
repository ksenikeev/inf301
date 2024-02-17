package ru.itis.inf301.lab2_2;

public class TestList<T> implements List301<T> {

    private Node head;
    private int size;

    @Override
    public void add(T e) {
        Node<T> n = new Node<T>(e);
        if (head == null) {
            head = n;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(n);
        }
        size++;
    }

    @Override
    public void delete(int index) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T get(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; ++i) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node current = head;
        String res = "";
        while (current != null) {
            res += ", " + current.getValue();
            current = current.getNext();
        }

        return res;
    }
}
