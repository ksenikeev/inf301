package ru.itis.inf301.lab13;

public class TestNextNumber {
    public static void main(String[] args) {
        NextNumberImpl numberFactory = new NextNumberImpl();

        for (int i = 0; i < 30; ++i)
            System.out.println(numberFactory.next());
    }
}
