package ru.itis.inf301.lab13.static_init;

public class TestInit {

    public Integer n;

    public static Integer m = 0;

    public TestInit() {
        m = 20;
        System.out.println(m);
    }

    static {
        m = 12;
        System.out.println(m);
    }
}
