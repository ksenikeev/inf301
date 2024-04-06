package ru.itis.inf301.lab2_6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainBufFileInputStream {
    public static void main(String[] args) {

        //InputStream is = null;
        try (InputStream is = new FileInputStream("transport.json")) {
            //is = new FileInputStream("transport.json");
            int b;
            while ((b = is.read()) != -1) {
                System.out.print(b);
                System.out.print(',');
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
