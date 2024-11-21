package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.List;
import java.util.LinkedList;



public class Controller implements Serializable {

    private static List<Event> data = new LinkedList<>();

    public Controller() {/* Because data is static */}
    
    public int getSize() { return data.size(); }

    public void addEvent(Event event) { data.add(event); }

    public boolean isElement(Event param){
        for(Event event : data){
            if(event.isSame(param)) return true;
        }
        return false;
    }

    //For debugging purposes only
    public void write(){
        for(Event event : data){
            System.out.println(event.toString());
        }
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
