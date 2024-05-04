package ru.itis.inf301.lab2_8.singleton;

public class SingletonObj {
    private static SingletonObj instance;

    private SingletonObj() {}

    public static SingletonObj getInstance() {
        if (instance == null) {
            synchronized (SingletonObj.class) {
                if (instance == null) {
                    instance = new SingletonObj();
                }
            }
        }
        return instance;
    }
}
