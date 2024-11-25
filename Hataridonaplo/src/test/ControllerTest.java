package test;

import code.Controller;
import code.Date;
import code.Event;
import code.Model;
import code.ShownEvent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.List;

public class ControllerTest {
    
    private Controller controller;
    private Event event1;
    private Event event3;

    @BeforeEach
    public void setUp(){
        controller = new Controller();
        event1 = new Event("", "", "", new Date(1000, 10, 1, 1, 1), new Date(1000, 11, 1, 1, 1));
        Event event2 = new Event(".", ".", ".", new Date(1000, 10, 1, 1, 1), new Date(1000, 11, 1, 1, 1));
        controller.clearModel();
        controller.addEventToModel(event2);
        event3 = new Event(",", ",", ",", new Date(1000, 1, 1, 1, 1), new Date(1000, 2, 1, 1, 1));
        controller.addEventToModel(event3);
    }

    @Test
    public void serializableTest(){
        int size = controller.getModelSize();
        controller.writeModel("src/test.ics", 10);
        int runNum = controller.readModel("src/test.ics");
        assertEquals(11, runNum);
        assertFalse(controller.isEmpty());
        assertEquals(size, controller.getModelSize());
    }

    @Test
    public void isEmptyTest(){
        assertFalse(controller.isEmpty());
        controller.clearModel();
        assertTrue(controller.isEmpty());
    }

    @Test
    public void addEventToModelTest(){
        int size = controller.getModelSize();
        controller.addEventToModel(event1);
        assertEquals(size + 1, controller.getModelSize());
    }

    @Test
    public void isElementOfModelTest(){
        assertTrue(controller.isElementOfModel(event3));
        assertTrue(controller.isElementOfModel(new Event(",", ",", ",", new Date(1000, 1, 1, 1, 1), new Date(1000, 2, 1, 1, 1))));
    }

    @Test
    public void deletePastEventsFromModelTest(){
        int size = controller.getModelSize();
        int removed = controller.deletePastEventsFromModel();
        assertEquals(size - removed, controller.getModelSize());
    }

    @Test
    public void deleteFromModelTest(){
        int size = controller.getModelSize();
        assertTrue(controller.isElementOfModel(event3));
        while(controller.isElementOfModel(event3)) controller.deleteFromModel(event3);
        assertFalse(controller.isElementOfModel(event3));
        assertEquals(size - 1, controller.getModelSize());
    }

    @Test
    public void sortDataTest(){
        Model model = new Model();
        assertNotSame(event3, model.getData().get(0));
        controller.sortData();
        assertSame(event3, model.getData().get(0));
    }

    @Test
    public void compareTests(){
        controller.addEventToModel(new Event("test", "test", "test", new Date(1000, 1, 1, 1, 1), new Date(1000, 1, 1, 1, 1)));
        List<Event> name = controller.nameCompare("test");
        assertEquals(1, name.size());
        List<Event> place = controller.placeCompare("test");
        assertEquals(1, place.size());
        List<Event> comment = controller.commentCompare("test");
        assertEquals(1, comment.size());
    }

    @Test
    public void toShownTest(){
        int size = controller.getModelSize();
        assertEquals(size, controller.toShown().size());
        controller.addEventToModel(new Event("test2", "test2", "test2", new Date(1000, 1, 1, 1, 1), new Date(1000, 1, 1, 1, 1)));
        List<Event> name = controller.nameCompare("test2");
        assertEquals(1, controller.toShownParameter(name).size());
    }

    @Test
    public void toShownWithCompareTests(){
        controller.addEventToModel(new Event("test3", "test3", "test3", new Date(2500, 1, 1, 1, 1), new Date(2500, 2, 1, 1, 1)));
        List<ShownEvent> sName = controller.toShownNameCompare("test3");
        assertEquals(1, sName.size());
        List<ShownEvent> sPlace = controller.toShownPlaceCompare("test3");
        assertEquals(1, sPlace.size());
        List<ShownEvent> sComment = controller.toShownCommentCompare("test3");
        assertEquals(1, sComment.size());
        List<ShownEvent> sBetween = controller.toShownBetweenDates(new Date(2499, 1, 1, 1, 1), new Date(2501, 1, 1, 1, 1));
        assertEquals(1, sBetween.size());
    }

    @Test
    public void getMaxDayTest(){
        assertEquals(29, controller.getMaxDay(2004, 2));
        assertEquals(28, controller.getMaxDay(2003, 2));
        assertEquals(30, controller.getMaxDay(1100, 4));
        assertEquals(30, controller.getMaxDay(1100, 6));
        assertEquals(30, controller.getMaxDay(1100, 9));
        assertEquals(30, controller.getMaxDay(1100, 11));
        assertEquals(31, controller.getMaxDay(1100, 1));
    }

}

//Remainder not tested as it contains graphical elements