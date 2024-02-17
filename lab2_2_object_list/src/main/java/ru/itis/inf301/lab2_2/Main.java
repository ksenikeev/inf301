package ru.itis.inf301.lab2_2;

public class Main {
    public static void main(String[] args) {
        TestList<Integer> testList = new TestList<Integer>();

        //testList.add('1');
        testList.add(2);
        testList.add(3);
        testList.add(4);

        System.out.println((Integer) testList.get(0) + (Integer)testList.get(1));

        System.out.println(testList);
    }
}
