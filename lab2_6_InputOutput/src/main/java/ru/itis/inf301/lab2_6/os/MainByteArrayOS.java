package ru.itis.inf301.lab2_6.os;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MainByteArrayOS {
    public static void main(String[] args) {

        byte[] data = {11,0,34,56,11,23,90,2,88};

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
/*
            for (int n : data) {
                os.write(n);
            }
*/
            os.write(data, 0, data.length);
            System.out.println(
                    Arrays.toString(os.toByteArray())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
