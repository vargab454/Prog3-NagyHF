package code;

import java.time.Year;

public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date() {}

    /**
     * Sets the stored variables to the given values
     * 
     * @param y year
     * @param m month
     * @param d day
     * @param h hour
     * @param mi minute
     */
    public Date(int y, int m, int d, int h, int mi){
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = mi;
    }

    /**
     * 
     * @param param the Date which we compare to
     * @return -1 if the parameter is bigger
     *         0 if the two Dates are the same
     *         1 if the parameter is smaller
     */
    public int compareTo(Date param){
        if(year == param.year && month == param.month && day == param.day && hour == param.hour && minute == param.minute) return 0;
        else if(year > param.year) return 1;
        else if(year == param.year && month > param.month) return 1;
        else if(year == param.year && month == param.month && day > param.day) return 1;
        else if(year == param.year && month == param.month && day == param.day && hour > param.hour) return 1;
        else if(year == param.year && month == param.month && day == param.day && hour == param.hour && minute > param.minute) return 1;
        else return -1;
    }

    /**
     * 
     * @return true if the Date is a valid Date that exists
     *         false if the Date is invalid
     */
    public boolean isValid(){
        if((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) return false;
        else if(month == 2 && isLeapYear() && (day == 30 || day == 31)) return false;
        else return !(month == 2 && !isLeapYear() && (day == 29 || day == 30 || day == 31));
    }

    /**
     * 
     * @return true if the stored year value represents a leapyear, false otherwise
     */
    public boolean isLeapYear(){
        return Year.of(year).isLeap();
    }

    /**
     * 
     * @return returns a String that contains the represented date in yyyy.mm.dd hh:mm format (doesnt check year as it is going to be a 4 digit number throughout the run)
     */
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

    /**
     * 
     * @return returns a String that contains the represented date in yyyymmddThhmm00 format (doesnt check year as it is going to be a 4 digit number throughout the run)
     */
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

    /**
     * 
     * @param param the String that we want to convert to Date
     * @return the Date that the param represents (converts from yyyymmddThhmmss format)
     */
    public Date toDate(String param){
        int myear = Integer.parseInt(param.substring(0, 4));
        int mmonth = Integer.parseInt(param.substring(4,6));
        int mday = Integer.parseInt(param.substring(6,8));
        int mhour = Integer.parseInt(param.substring(9,11));
        int mminute = Integer.parseInt(param.substring(11, 13));
        return new Date(myear, mmonth, mday, mhour, mminute);
    }
}
