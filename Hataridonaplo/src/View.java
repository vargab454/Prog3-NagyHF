import java.util.*;

public class View {

    private static List<Event> data = new LinkedList<>();

    public View() { /* Because data is static */}
    
    public int getSize() { return data.size(); }

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
