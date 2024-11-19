import java.util.*;

public class View {

    private static List<Event> data;

    public View(){
        data = new LinkedList<>();
    }
    
    public int getSize() { return data.size(); }

    public void add(String name, String place, String comment, int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute) { data.add(new Event(name, place, comment, startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute)); }

    public void addEvent(Event event) { data.add(event); }

    public boolean isElement(Event param){
        for(Event event : data){
            if(event.isSame(param)) return true;
        }
        return false;
    }

    public void write(){
        for(Event event : data){
            System.out.println(event.toString());
        }
    }
}
