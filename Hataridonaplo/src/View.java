import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void save(){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter("data.ics"));
            pw.write("BEGIN:VCALENDAR\r\nVERSION:2.0\r\nCALSCALE:GREGORIAN\r\nPRODID:-//proba//proba 1.0//EN");
            int idx = 1;
            for(Event event : data){
                System.out.println("\r\nBEGIN:VEVENT\r\nUID:v1-" + idx +"\r\nSUMMARY:" + event.getName() + "\r\nDTSTART:" + event.getStart() + "\r\nDTEND:" + event.getEnd() + "\r\nLOCATION:" + event.getPlace() + "\r\nDESCRIPTION:" + event.getComment() + "\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT");
                pw.write("\r\nBEGIN:VEVENT\r\nUID:v1-" + idx +"\r\nSUMMARY:" + event.getName() + "\r\nDTSTART:" + event.getStart() + "\r\nDTEND:" + event.getEnd() + "\r\nLOCATION:" + event.getPlace() + "\r\nDESCRIPTION:" + event.getComment() + "\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT");
                idx++;
            }
            pw.write("\r\nEND:VCALENDAR");
            pw.close();
        }catch(IOException e){ System.out.println("Sikertelen kiiratás."); }
    }
}
