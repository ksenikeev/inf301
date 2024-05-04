package ru.itis.inf301.lab2_6.file;

import java.io.File;
import java.util.Arrays;

public class MainFile {

    public static void main(String[] args) {
        File dir = new File("dir");
        //dir.mkdir();

        File file = new File("/home/ksenikeev/ideaprojects/inf301");
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(dir.isDirectory());
        System.out.println(file.getParentFile());

        // список файлов
        Arrays.stream(file.listFiles()).forEach(System.out::println);
        //dir.delete();
    }
}
