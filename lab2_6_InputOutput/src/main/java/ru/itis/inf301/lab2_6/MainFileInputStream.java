package ru.itis.inf301.lab2_6;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainFileInputStream {
    public static void main(String[] args) {

        //InputStream is = null;
        try (InputStream is = new FileInputStream("transport.json")) {
            //is = new FileInputStream("transport.json");
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

/*
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
*/

    }
}
