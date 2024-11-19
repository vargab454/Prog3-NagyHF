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

    public String toString(){
        return year + "." + month + "." + day + " " + hour + ":" + minute;
    }
}
