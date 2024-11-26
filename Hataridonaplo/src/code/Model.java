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

    /**
     * LinkedList that contains the stored Events
     */
    private static List<Event> data = new LinkedList<>();

    public Model() {/* No need to do anything */}

    /**
     * 
     * @param event the event that will be added to the stored list
     * 
     * Adds the event given as the parameter to the stored list
     */
    public void addEvent(Event event) { data.add(event); }

    /**
     * 
     * @return the stored list
     */
    public List<Event> getData() { return data; }

    /**
     * 
     * @param param the Event that will be deleted
     * 
     * Deletes the Event given as the parameter
     */
    public void delete(Event param) { data.remove(param); }

    /**
     * Empties the stored list
     */
    public void clear() { data.clear(); }

    /**
     * 
     * @param param the Event that will be modified
     * @param name the new name value
     * 
     * Sets the given parameter's name to the given value
     */
    public void modifyName(Event param, String name) { data.get(data.indexOf(param)).setName(name); }

    /**
     * 
     * @param param the Event that will be modified
     * @param place the new place value
     * 
     * Sets the given parameter's place to the given value
     */
    public void modifyPlace(Event param, String place) { data.get(data.indexOf(param)).setPlace(place); }

    /**
     * 
     * @param param the Event that will be modified
     * @param comment the new comment value
     * 
     * Sets the given parameter's comment to the given value
     */
    public void modifyComment(Event param, String comment) { data.get(data.indexOf(param)).setComment(comment); }

    /**
     * 
     * @return the number of past Events deleted
     * 
     * Deletes all the stored Events that have an end date that is smaller than the current time
     */
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

    /**
     * 
     * @param filename the file's name that we will write into
     * @param r is used in order to create uniqe IDs when data is saved
     * 
     * Creates the .ics file format stated in the specification
     * Sets the timezone to UTC+1
     * unique ids -> v + r + - + idx where idx represents the given Event's place in the stored List
     */
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

    /**
     * 
     * @param filename the file that will be read
     * @return the runNum that has been read from the given file (from PRODID line)
     * 
     * Reads the file that was given
     * Should be the same format as the program creates as it reads exactly the same way as it writes (searializable data)
     */
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
