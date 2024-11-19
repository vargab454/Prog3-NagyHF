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
        if(name.equals(param.name) && place.equals(param.place) && comment.equals(param.comment) && compareTo(param) == 0) return true;
        else return false;
    }

    public String toString() { return "Név: " + name + "\nHelyszín: " + place + "\nKomment: " + comment + "\nKezdo idopont: " + start.toString() + "\nZáró idopont: " + end.toString(); }
}
