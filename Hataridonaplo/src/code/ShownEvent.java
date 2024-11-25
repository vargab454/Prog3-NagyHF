package code;

public class ShownEvent {

    private String name;
    private String place;
    private String comment;
    private String start;
    private String end;
    
    public ShownEvent(String n, String p, String c, String s, String e){
        name = n;
        place = p;
        comment = c;
        start = s;
        end = e;
    }
    
    public String getName() { return name; }
    
    public String getPlace() { return place; }
    
    public String getComment() { return comment; }
    
    public String getStart() { return start; }
    
    public String getEnd() { return end; }

    public String toString() { return name + "; " + place + "; " + comment + "; " + start + "; " + end; }
}
