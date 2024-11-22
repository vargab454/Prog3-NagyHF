package code;

import java.time.Year;

public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date() {}

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
        else return !(month == 2 && !isLeapYear() && (day == 29 || day == 30 || day == 31));
    }

    public boolean isLeapYear(){
        return Year.of(year).isLeap();
    }

    public String toString(){
        String monthOut = "";
        String dayOut = "";
        String hourOut = "";
        String minuteOut = "";
        if(month < 10) monthOut = "0";
        monthOut += month;
        if(day < 10) dayOut = "0";
        dayOut += day;
        if(hour < 10) hourOut = "0";
        hourOut += hour;
        if(minute < 10) minuteOut = "0";
        minuteOut += minute;
        return year + "." + monthOut + "." + dayOut + " " + hourOut + ":" + minuteOut;
    }

    public String toFileFormatString(){
        String ret = "";
        ret += year;
        if(month < 10){
            ret += '0';
            ret += month;
        }else ret += month;
        if(day < 10){
            ret += '0';
            ret += day;
        }else ret += day;
        ret += 'T';
        if(hour < 10){
            ret += '0';
            ret += hour;
        }else ret += hour;
        if(minute < 10){
            ret += '0';
            ret += minute;
        }else ret += minute;
        ret += "00";
        return ret;
    }

    public Date toDate(String param){
        int myear = Integer.parseInt(param.substring(0, 4));
        int mmonth = Integer.parseInt(param.substring(4,6));
        int mday = Integer.parseInt(param.substring(6,8));
        int mhour = Integer.parseInt(param.substring(9,11));
        int mminute = Integer.parseInt(param.substring(11, 13));
        return new Date(myear, mmonth, mday, mhour, mminute);
    }
}
