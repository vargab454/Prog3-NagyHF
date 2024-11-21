package code;

public class Event {

    private String name;
    private String place;
    private String comment;
    private Date start;
    private Date end;
    
    public Event(String n, String p, String c, Date s, Date e){
        name = n;
        place = p;
        comment = c;
        start = s;
        end = e;
    }

    //Compares strictly based on startdate
    public int compareTo(Event param){
        return start.compareTo(param.start);
    }

    public boolean isSame(Event param){
        return (name.equals(param.name) && place.equals(param.place) && comment.equals(param.comment) && compareTo(param) == 0);
    }

    //For debugging purposes only
    public String toString() { return "Név: " + name + "\nHelyszín: " + place + "\nKomment: " + comment + "\nKezdo idopont: " + start.toString() + "\nZáró idopont: " + end.toString(); }

    public String toFileFormat(int runNum, int idx) { return "\r\nBEGIN:VEVENT\r\nUID:v" + runNum + "-" + idx +"\r\nSUMMARY:" + name + "\r\nDTSTART;TZID=UTC+01:" + start.toString() + "\r\nDTEND;TZID=UTC+01:" + end.toString() + "\r\nLOCATION:" + place + "\r\nDESCRIPTION:" + comment + "\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT";}
}
