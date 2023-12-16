package ru.itis.inf301.lab13;

public class NextNumberImpl implements NextNumber {

    private Node current;

    public NextNumberImpl() {
        Node root = new Node(1);
        current = root;

        for (int i = 2; i <= 10; ++i) {
            current.next = new Node(i);
            current = current.next;
        }

        current.next = root;
        System.out.println();
    }

    @Override
    public int next() {
        current = current.next;
        int result = current.value;
        return result;
    }
}
