package ru.itis.inf301.lab2_6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainBufferedReader {
    public static void main(String[] args) {

        try (Reader is = new InputStreamReader(new FileInputStream("transport.json"))) {
            int b;
            while ((b = is.read()) != -1) {
                System.out.print((char)b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
