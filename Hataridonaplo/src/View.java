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

    public void save(String filename){
        int runNum = 3;
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.write("BEGIN:VCALENDAR\r\nVERSION:2.0\r\nCALSCALE:GREGORIAN\r\nPRODID:-//Hataridonaplo//run " + runNum +"//EN");
            int idx = 1;
            for(Event event : data){
                pw.write(event.toFileFormat(runNum, idx));
                idx++;
            }
            pw.write("\r\nEND:VCALENDAR");
            pw.close();
        }catch(IOException e){ System.out.println("Sikertelen kiiratás."); }
    }
}
