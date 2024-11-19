import java.sql.Date;

public class Event {

    private String name;
    private String place;
    private String comment;
    private Date startDate;
    private Date endDate;
    
    public Event(String n, String p, String c, Date s, Date e){
        name = n;
        place = p;
        comment = c;
        startDate = s;
        endDate = e;
    }

    public int compareTo(Event param){
        return startDate.compareTo(param.startDate);
    }
}
