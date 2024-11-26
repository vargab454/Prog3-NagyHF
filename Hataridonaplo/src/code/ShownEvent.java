package code;

public class ShownEvent {

    private String name;
    private String place;
    private String comment;
    private String start;
    private String end;
    
    /**
     * 
     * @param n name
     * @param p place
     * @param c comment
     * @param s start date as String
     * @param e end date as String
     * 
     * Sets the stored variables to the given values
     */
    public ShownEvent(String n, String p, String c, String s, String e){
        name = n;
        place = p;
        comment = c;
        start = s;
        end = e;
    }
    
    /**
     * 
     * @return the stored name as a String
     */
    public String getName() { return name; }
    
    /**
     * 
     * @return the stored place as a String
     */
    public String getPlace() { return place; }
    
    /**
     * 
     * @return the stored comment as a String
     */
    public String getComment() { return comment; }
    
    /**
     * 
     * @return the stored start date as a String
     */
    public String getStart() { return start; }

    /**
     * 
     * @return the stored end date as a String
     */
    public String getEnd() { return end; }

    /**
     * 
     * @return a String that represents the ShownDate (all the attributes separated by '; ')
     */
    public String toString() { return name + "; " + place + "; " + comment + "; " + start + "; " + end; }
}
