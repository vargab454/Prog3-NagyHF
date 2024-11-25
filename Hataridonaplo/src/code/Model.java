package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.LinkedList;

public class Model{

    private static List<Event> data = new LinkedList<>();

    public Model() {/* No need to do anything */}

    public void addEvent(Event event) { data.add(event); }

    public List<Event> getData() { return data; }

    public void delete(Event param) { data.remove(param); }

    public void clear() { data.clear(); }

    public void modifyName(Event param, String name) { data.get(data.indexOf(param)).setName(name); }

    public void modifyPlace(Event param, String place) { data.get(data.indexOf(param)).setPlace(place); }

    public void modifyComment(Event param, String comment) { data.get(data.indexOf(param)).setComment(comment); }

    public int deletePastEvents() {
        int deletedNum = 0;
        LocalDateTime now = LocalDateTime.now();
        Date myNow = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
        List<Event> tempData = new LinkedList<>();
        tempData.addAll(data);
        for(Event event : data){
            if(event.getEnd().compareTo(myNow) == -1){
                tempData.remove(event);
                deletedNum++;
            }
        }
        data.clear();
        data.addAll(tempData);
        return deletedNum;
    }

    public void write(String filename, int r){
        int runNum = r + 1;
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.write("BEGIN:VCALENDAR\r\nVERSION:2.0\r\nCALSCALE:GREGORIAN\r\nPRODID:-//Hataridonaplo//run " + runNum + "//EN\r\nBEGIN:VTIMEZONE\r\nTZID:UTC+01\r\nBEGIN:STANDARD\r\nDTSTART:19700101T000000\r\nTZOFFSETFROM:+0100\r\nTZOFFSETTO:+0100\r\nTZNAME:UTC+1\r\nEND:STANDARD\r\nEND:VTIMEZONE");
            int idx = 1;
            for(Event event : data){
                pw.write(event.toFileFormat(runNum, idx));
                idx++;
            }
            pw.write("\r\nEND:VCALENDAR");
            pw.close();
        }catch(IOException e){ System.out.println("Sikertelen kiiratás."); }
    }

    public int read(String filename){
        clear();
        int runNum = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String currentLine = "";
            for(int i = 0; i < 4; i++) currentLine = br.readLine();
            runNum = Integer.parseInt(currentLine.substring(29, currentLine.length() - 4));
            for(int i = 0; i < 9; i++) currentLine = br.readLine();
            while(!br.readLine().equals("END:VCALENDAR")){
                for(int i = 0; i < 2; i++) currentLine = br.readLine();
                String name = currentLine.substring(8);
                Date start = new Date();
                start = start.toDate(br.readLine().substring(20));
                Date end = new Date();
                end = end.toDate(br.readLine().substring(18));
                String place = br.readLine().substring(9);
                String comment = br.readLine().substring(12);
                for(int i = 0; i < 2; i++) currentLine = br.readLine();
                data.add(new Event(name, place, comment, start, end));
            }
            br.close();
        }catch(FileNotFoundException e) { System.out.println("Nem létezik a kívánt fájl."); }
        catch(IOException e) { System.out.println("Sikertelen beolvasás."); }
        return runNum;
    }
}
