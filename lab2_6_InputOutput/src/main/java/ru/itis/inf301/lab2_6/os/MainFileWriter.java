package ru.itis.inf301.lab2_6.os;

import java.io.*;
import java.util.Scanner;

public class MainFileWriter {
    public static void main(String[] args) {

        try (Writer fw = new FileWriter("test_writer.log", true)) {

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String str = scanner.next() + "\n";
                fw.write(str);
            }

            fw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileReader fr;
        DataInputStream di;
    }
}
