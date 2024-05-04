package ru.itis.inf301.lab2_7;

import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void testFormatDate() {
        String testStrDate = "20.04.2024";
        Date testDate = null;
        try {
            testDate = new SimpleDateFormat("dd.MM.yyyy").parse(testStrDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(testStrDate, DateUtil.formatDate(testDate));
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionFormatDate() {
        DateUtil.formatDate(null);
    }

    @Test
    public void testFailFormatDate() {
        String testStrDate = "21.04.2024";
        Date testDate = null;
        try {
            testDate = new SimpleDateFormat("dd.MM.yyyy").parse(testStrDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (!testStrDate.equals( DateUtil.formatDate(new Date()))) {
            Assert.fail("Метод не прошел промерку");
        }
    }
}