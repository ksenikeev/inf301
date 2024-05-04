package ru.itis.inf301.lab2_6.os;

import java.io.*;

public class MainDataOS {
    public static void main(String[] args) {
        int[] data = {11, 0, 34, 56, 11, 23, 90, 2, 88};

        try (DataOutputStream os =
                     new DataOutputStream(new BufferedOutputStream(
                             new FileOutputStream("test_fos.bin")))) {
            for (int n : data) {
                os.writeInt(n);
            }
            //os.write(data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
