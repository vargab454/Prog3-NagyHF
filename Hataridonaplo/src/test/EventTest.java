package test;

import code.Event;
import code.Date;
import code.ShownEvent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    
    Date start;
    Date end;
    Event event;

    @BeforeEach
    public void setUp(){
        start = new Date(2026, 10, 10, 20, 20);
        end = new Date(2026, 11, 11, 20, 20);
        event = new Event("Név", "Helyszín", "Megjegyzés", start, end);
    }

    @Test
    public void getterTest(){
        assertEquals("Név", event.getName());
        assertEquals("Helyszín", event.getPlace());
        assertEquals("Megjegyzés", event.getComment());
        assertEquals(end.toString(), event.getEnd().toString());
    }

    @Test
    public void setterTest(){
        event.setName("name");
        event.setPlace("place");
        event.setComment("comment");
        assertEquals("name", event.getName());
        assertEquals("place", event.getPlace());
        assertEquals("comment", event.getComment());
    }

    @Test
    public void compareToTest(){
        Date s = new Date(2026, 10, 10, 20, 21);
        Date e = new Date(2026, 10, 10, 20, 22);
        Event compare = new Event("", "", "", s, e);
        assertEquals(-1, event.compareTo(compare));
    }

    @Test
    public void isActiveBetweenTest(){
        Date before = new Date(2024, 10, 10, 20, 20);
        Date before2 = new Date(2022, 10, 10, 20, 20);
        Date between = new Date(2026, 10, 10, 30, 30);
        Date between2 = new Date(2026, 10, 10, 40, 40);
        Date after = new Date(2028, 10, 10, 20, 20);
        Date after2 = new Date(2030, 10, 10, 20, 20);
        assertTrue(event.isActiveBetween(before, between));
        assertTrue(event.isActiveBetween(before, after));
        assertTrue(event.isActiveBetween(between, after));
        assertFalse(event.isActiveBetween(after, after2));
        assertFalse(event.isActiveBetween(before, before2));
        assertTrue(event.isActiveBetween(between, between2));
    }

    @Test
    public void isSameTest(){
        Date date = new Date(2020, 10, 10, 20, 20);
        assertTrue(event.isSame(new Event("Név", "Helyszín", "Megjegyzés", start, end)));
        assertFalse(event.isSame(new Event("name", "Helyszín", "Megjegyzés", start, end)));
        assertFalse(event.isSame(new Event("Név", "place", "Megjegyzés", start, end)));
        assertFalse(event.isSame(new Event("Név", "Helyszín", "comment", start, end)));
        assertFalse(event.isSame(new Event("Név", "Helyszín", "Megjegyzés", date, end)));
        assertFalse(event.isSame(new Event("Név", "Helyszín", "Megjegyzés", start, date)));
    }

    @Test
    public void toFileFormatTest(){
        assertEquals("\r\nBEGIN:VEVENT\r\nUID:v1-1\r\nSUMMARY:Név\r\nDTSTART;TZID=UTC+01:20261010T202000\r\nDTEND;TZID=UTC+01:20261111T202000\r\nLOCATION:Helyszín\r\nDESCRIPTION:Megjegyzés\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT", event.toFileFormat(1, 1));
    }

    //Tests all of ShownEvent's methods as well
    @Test
    public void toShownEventTest(){
        ShownEvent sEvent = event.toShownEvent();
        assertEquals(sEvent.getName(), "Név");
        assertEquals(sEvent.getPlace(), "Helyszín");
        assertEquals(sEvent.getComment(), "Megjegyzés");
        assertEquals(sEvent.getStart(), "2026.10.10 20:20");
        assertEquals(sEvent.getEnd(), "2026.11.11 20:20");
        assertEquals(sEvent.toString(), "Név; Helyszín; Megjegyzés; 2026.10.10 20:20; 2026.11.11 20:20");
    }
}
