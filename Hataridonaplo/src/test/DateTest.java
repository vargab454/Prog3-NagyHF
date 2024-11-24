package test;

import code.Date;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTest {

    @Test
    public void comapreToTest(){
        Date date1 = new Date(2025, 11, 29, 20, 20);
        Date date2 = new Date(2025, 11, 29, 20, 20);
        Date date3 = new Date(2025, 11, 29, 20, 19);
        Date date4 = new Date(2025, 11, 29, 19, 20);
        Date date5 = new Date(2025, 11, 28, 20, 20);
        Date date6 = new Date(2025, 10, 29, 20, 20);
        Date date7 = new Date(2024, 11, 29, 20, 20);
        Date date8 = new Date(2025, 11, 29, 20, 21);
        Date date9 = new Date(2025, 11, 29, 21, 20);
        Date date10 = new Date(2025, 11, 30, 20, 20);
        Date date11 = new Date(2025, 12, 29, 20, 20);
        Date date12 = new Date(2026, 11, 29, 20, 20);
        assertEquals(0, date1.compareTo(date2));
        assertEquals(1, date1.compareTo(date3));
        assertEquals(1, date1.compareTo(date4));
        assertEquals(1, date1.compareTo(date5));
        assertEquals(1, date1.compareTo(date6));
        assertEquals(1, date1.compareTo(date7));
        assertEquals(-1, date1.compareTo(date8));
        assertEquals(-1, date1.compareTo(date9));
        assertEquals(-1, date1.compareTo(date10));
        assertEquals(-1, date1.compareTo(date11));
        assertEquals(-1, date1.compareTo(date12));
    }

    @Test
    public void isValidTest(){
        Date date1 = new Date(2026, 4, 31, 20, 20);
        Date date2 = new Date(2026, 6, 31, 20, 20);
        Date date3 = new Date(2026, 9, 31, 20, 20);
        Date date4 = new Date(2026, 11, 31, 20, 20);
        Date date5 = new Date(2026, 4, 30, 20, 20);
        Date date6 = new Date(2024, 2, 29, 20, 20);
        Date date7 = new Date(2024, 2, 30, 20, 20);
        Date date8 = new Date(2024, 2, 31, 20, 20);
        Date date9 = new Date(2025, 2, 29, 20, 20);
        Date date10 = new Date(2025, 2, 30, 20, 20);
        Date date11 = new Date(2025, 2, 31, 20, 20);
        Date date12 = new Date(2025, 2, 28, 20, 20);
        assertFalse(date1.isValid());
        assertFalse(date2.isValid());
        assertFalse(date3.isValid());
        assertFalse(date4.isValid());
        assertTrue(date5.isValid());
        assertTrue(date6.isValid());
        assertFalse(date7.isValid());
        assertFalse(date8.isValid());
        assertFalse(date9.isValid());
        assertFalse(date10.isValid());
        assertFalse(date11.isValid());
        assertTrue(date12.isValid());
    }

    @Test
    public void toStringTest(){
        Date date1 = new Date(2026,1,1,1,1);
        Date date2 = new Date(2026, 12, 13, 32, 51);
        assertEquals("2026.01.01 01:01", date1.toString());
        assertEquals("2026.12.13 32:51", date2.toString());
    }

    @Test
    public void toFileFormatStringTest(){
        Date date = new Date(2027,1,1,1,1);
        Date date2 = new Date(2026, 12, 13, 32, 51);
        assertEquals("20270101T010100", date.toFileFormatString());
        assertEquals("20261213T325100", date2.toFileFormatString());
    }

    @Test
    public void toDateTest(){
        String string = "20381225T532900";
        Date date = new Date(2038,12,25,53,29);
        assertEquals(date.toString(), new Date().toDate(string).toString());
    }
}
