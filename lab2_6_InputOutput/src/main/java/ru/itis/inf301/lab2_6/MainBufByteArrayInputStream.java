package ru.itis.inf301.lab2_6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainBufByteArrayInputStream {
    public static void main(String[] args) {

        byte[] data = {12,-90,11,23,44,0,12,-7,11,18,3,22,84,10,23,16};

        try {
            InputStream is = new ByteArrayInputStream(data);
            int b;
            byte[] buffer = new byte[5];
            while ((b = is.read(buffer)) != -1) {
                for(int i = 0; i < b; i++) {
                    System.out.print(buffer[i] & 0xFF);
                    System.out.print(',');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
