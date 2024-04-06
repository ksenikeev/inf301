package ru.itis.inf301.lab2_6;

import java.io.*;

public class MainInputStreamReader {
    public static void main(String[] args) {

        try (Reader is = new BufferedReader(
                            new InputStreamReader(
                            new FileInputStream("transport.json")))) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.print((char)b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
