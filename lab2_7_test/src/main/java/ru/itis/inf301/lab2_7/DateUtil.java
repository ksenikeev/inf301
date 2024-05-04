package ru.itis.inf301.lab2_7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date) throws NullPointerException {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
}
