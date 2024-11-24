package test;

import code.Model;
import code.Event;
import code.Date;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ModelTest {
    
    private Model model;
    private Event event1;

    @BeforeEach
    public void setUp(){
        model = new Model();
        event1 = new Event("", "", "", new Date(1000, 1, 1, 1, 1), new Date(1000, 1, 1, 1, 1));
        model.addEvent(event1);
        Event event2 = new Event("", "", "", new Date(LocalDateTime.now().getYear() + 1, 1, 1, 1, 1), new Date(LocalDateTime.now().getYear() + 1, 10, 10, 10, 10));
        model.addEvent(event2);
    }

    @Test
    public void addEventTest(){
        int size = model.getData().size();
        Event event = new Event("", "", "", new Date(1000, 1, 1, 1, 1), new Date(1000, 1, 1, 1, 1));
        model.addEvent(event);
        assertEquals(size + 1, model.getData().size());
        assertSame(model.getData().get(size), event);
    }

    @Test
    public void deleteTest(){
        int size = model.getData().size();
        model.delete(event1);
        assertEquals(size - 1, model.getData().size());
    }

    @Test
    public void modifyTest(){
        int idx = model.getData().indexOf(event1);
        assertNotEquals(model.getData().get(idx).getName(), "Név");
        model.modifyName(event1, "Név");
        assertEquals(model.getData().get(idx).getName(), "Név");
        assertNotEquals(model.getData().get(idx).getPlace(), "Helyszín");
        model.modifyPlace(event1, "Helyszín");
        assertEquals(model.getData().get(idx).getPlace(), "Helyszín");
        assertNotEquals(model.getData().get(idx).getComment(), "Megjegyzés");
        model.modifyComment(event1, "Megjegyzés");
        assertEquals(model.getData().get(idx).getComment(), "Megjegyzés");
    }

    @Test
    public void searializableTest(){
        model.write("src/test.ics", 10);
        int size = model.getData().size();
        int runNum = model.read("src/test.ics");
        assertEquals(11, runNum);
        model.read("src/test.ics");
        assertEquals(size, model.getData().size());
    }
    
    @Test
    public void deletePastEventsTest(){
        model.deletePastEvents();
        assertEquals(1, model.getData().size());
    }

}
