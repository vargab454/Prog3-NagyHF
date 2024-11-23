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

    public String getName() { return name; }

    public String getPlace() { return place; }

    public String getComment() { return comment; }

    public Date getEnd() { return end; }

    //Compares strictly based on startdate
    public int compareTo(Event param){
        return start.compareTo(param.start);
    }

    public boolean isActiveBetween(Date param1, Date param2){
        return ((start.compareTo(param1) != -1 && start.compareTo(param2) != 1) || (end.compareTo(param1) != -1 && end.compareTo(param2) != 1) || start.compareTo(param1) == -1 && end.compareTo(param2) == 1);
    }

    public boolean isSame(Event param){
        return (name.equals(param.name) && place.equals(param.place) && comment.equals(param.comment) && compareTo(param) == 0);
    }

    public String toFileFormat(int runNum, int idx) { return "\r\nBEGIN:VEVENT\r\nUID:v" + runNum + "-" + idx +"\r\nSUMMARY:" + name + "\r\nDTSTART;TZID=UTC+01:" + start.toFileFormatString() + "\r\nDTEND;TZID=UTC+01:" + end.toFileFormatString() + "\r\nLOCATION:" + place + "\r\nDESCRIPTION:" + comment + "\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT";}

    public ShownEvent toShownEvent() { return new ShownEvent(name, place, comment, start.toString(), end.toString()); }
}
