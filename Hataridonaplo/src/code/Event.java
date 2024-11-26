package code;

public class Event {

    private String name;
    private String place;
    private String comment;
    private Date start;
    private Date end;
    
    /**
     * Sets the stored variables to the given values
     * 
     * @param n name
     * @param p place
     * @param c comment
     * @param s start Date
     * @param e end Date
     */
    public Event(String n, String p, String c, Date s, Date e){
        name = n;
        place = p;
        comment = c;
        start = s;
        end = e;
    }

    /**
     * 
     * @return returns the stored name as a String
     */
    public String getName() { return name; }

    /**
     * 
     * @return returns the place name as a String
     */
    public String getPlace() { return place; }

    /**
     * 
     * @return returns the stored comment as a String
     */
    public String getComment() { return comment; }

    /**
     * 
     * @return returns the stored end date as a Date
     */
    public Date getEnd() { return end; }

    /**
     * Sets the stored name to the value given
     * 
     * @param n name
     */
    public void setName(String n) { name = n; }

    /**
     * Sets the stored place to the value given
     * 
     * @param p place
     */
    public void setPlace(String p) { place = p; }

    /**
     * Sets the stored comment to the comment given
     * 
     * @param c comment
     */
    public void setComment(String c) { comment = c; }

    /**
     * 
     * @param param the event that the comparation will be based on
     * @return -1 if the parameter's stored start date is bigger
     *         0 if the two stored start dates are the same
     *         1 if the parameter's stored start date is smaller
     */
    public int compareTo(Event param){
        return start.compareTo(param.start);
    }

    /**
     * 
     * @param param1 a Date that we will compare to
     * @param param2 another Date that we will compare to
     * @return true if the Event has a portion that fits the interval between the two parameters, false otherwise
     */
    public boolean isActiveBetween(Date param1, Date param2){
        if(param1.compareTo(param2) == 1){
            Date temp = param1;
            param1 = param2;
            param2 = temp;
        }
        return ((start.compareTo(param1) != -1 && start.compareTo(param2) != 1) || (end.compareTo(param1) != -1 && end.compareTo(param2) != 1) || start.compareTo(param1) == -1 && end.compareTo(param2) == 1);
    }

    /**
     * 
     * @param param an Event that will be compared to the actual one
     * @return true if all the stored data is the same, false otherwise
     */
    public boolean isSame(Event param){
        return (name.equals(param.name) && place.equals(param.place) && comment.equals(param.comment) && compareTo(param) == 0 && end.toString().equals(param.getEnd().toString()));
    }

    /**
     * 
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param idx is used in order to create uniqe IDs when data is saved
     * @return a String that fits the .ics requirements
     */
    public String toFileFormat(int runNum, int idx) { return "\r\nBEGIN:VEVENT\r\nUID:v" + runNum + "-" + idx +"\r\nSUMMARY:" + name + "\r\nDTSTART;TZID=UTC+01:" + start.toFileFormatString() + "\r\nDTEND;TZID=UTC+01:" + end.toFileFormatString() + "\r\nLOCATION:" + place + "\r\nDESCRIPTION:" + comment + "\r\nDTSTAMP:20241219T120000Z\r\nEND:VEVENT";}

    /**
     * 
     * @return a ShownEvent that contains the same data as the current object, but the Dates are replaced by Strings
     */
    public ShownEvent toShownEvent() { return new ShownEvent(name, place, comment, start.toString(), end.toString()); }
}
