
public class Event {

    private String name;
    private String place;
    private String comment;
    private Date start;
    private Date end;
    
    public Event(String n, String p, String c, int sy, int sm, int sd, int sh, int smi, int ey, int em, int ed, int eh, int emi){
        name = n;
        place = p;
        comment = c;
        start = new Date(sy, sm, sd, sh, smi);
        end = new Date(ey, em, ed, eh, emi);
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
