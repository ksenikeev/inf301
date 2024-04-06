package ru.itis.inf301.lab2_6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainByteArrayInputStream {
    public static void main(String[] args) {

        byte[] data = {12,-90,11,23,44,0,12,-7,11,18,3,22,84,10,23,16};

        try {
            InputStream is = new ByteArrayInputStream(data);
            int b;
            while ((b = is.read()) != -1) {
                System.out.print(b);
                System.out.print(',');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
