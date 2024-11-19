import java.util.Calendar;

public class Event {

    private String name;
    private String place;
    private String comment;
    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMinute;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinute;
    
    public Event(String n, String p, String c, int sy, int sm, int sd, int sh, int smi, int ey, int em, int ed, int eh, int emi){
        name = n;
        place = p;
        comment = c;
        startYear = sy;
        startMonth = sm;
        startDay = sd;
        startHour = sh;
        startMinute = smi;
        endYear = ey;
        endMonth = em;
        endDay = ed;
        endHour = eh;
        endMinute = emi;
    }

    public int compareTo(Event param) {
        if(startYear == param.startYear && startMonth == param.startMonth && startDay == param.startDay && startHour == param.startHour && startMinute == param.startMinute) return 0;
        else if(startYear > param.startYear) return 1;
        else if(startYear == param.startYear && startMonth > param.startMonth) return 1;
        else if(startYear == param.startYear && startMonth == param.startMonth && startDay > param.startDay) return 1;
        else if(startYear == param.startYear && startMonth == param.startMonth && startDay == param.startDay && startHour > param.startHour) return 1;
        else if(startYear == param.startYear && startMonth == param.startMonth && startDay == param.startDay && startHour == param.startHour && startMinute > param.startMinute) return 1;
        else return -1;
    }

    public String toString() { return "Név: " + name + "\nHelyszín: " + place + "\nKomment: " + comment + "\nKezdo idopont: " + startYear + "." + startMonth + "." + startDay + " " + startHour + ":" + startMinute + "\nZáró idopont: " + endYear + "." + endMonth + "." + endDay + " " + endHour + ":" + endMinute; }
}
