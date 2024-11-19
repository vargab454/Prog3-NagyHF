import java.time.Year;

public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(int y, int m, int d, int h, int mi){
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = mi;
    }

    public int compareTo(Date param){
        if(year == param.year && month == param.month && day == param.day && hour == param.hour && minute == param.minute) return 0;
        else if(year > param.year) return 1;
        else if(year == param.year && month > param.month) return 1;
        else if(year == param.year && month == param.month && day > param.day) return 1;
        else if(year == param.year && month == param.month && day == param.day && hour > param.hour) return 1;
        else if(year == param.year && month == param.month && day == param.day && hour == param.hour && minute > param.minute) return 1;
        else return -1;
    }

    public boolean isValid(){
        if((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) return false;
        else if(month == 2 && isLeapYear() && (day == 30 || day == 31)) return false;
        else if(month == 2 && !isLeapYear() && (day == 29 || day == 30 || day == 31)) return false;
        else return true;
    }

    public boolean isLeapYear(){
        return Year.of(year).isLeap();
    }

    public String toString(){
        return year + "." + month + "." + day + " " + hour + ":" + minute;
    }
}
