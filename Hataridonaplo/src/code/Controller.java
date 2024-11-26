package code;

import java.awt.Dimension;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

public class Controller{
    
    private Model model;

    public Controller(){ model = new Model(); }

    /**
     * Calls model's appropriate function with the given parameters
     * 
     * @param filename the file's name that we will write into
     * @param runNum is used in order to create uniqe IDs when data is saved
     */
    public void writeModel(String filename, int runNum){ model.write(filename, runNum); }

    /**
     * Calls model's appropriate function with the given parameters
     * 
     * @param filename the file that we will read from
     * @return the runNum that has been read from the given file (from PRODID line)
     */
    public int readModel(String filename){ return model.read(filename); }

    /**
     * 
     * @return the number of Events currently stored in model
     */
    public int getModelSize(){ return model.getData().size(); }
    
    /**
     * 
     * @return true if the model is empty, false otherwise
     */
    public boolean isEmpty() { return getModelSize() == 0; }

    /**
     * Calls model's appropriate function with the given parameters
     * 
     * @param param the Event that will be added to the stored List
     */
    public void addEventToModel(Event param){ model.addEvent(param); }

    /**
     * Clears model by calling it's clear function
     */
    public void clearModel() { model.clear(); }

    /**
     * Checks all the stored Events if any are the same as the parameter
     * 
     * @param param the Event that we want to get information about
     * @return true if there is a stored element with the exact same data as the parameter, false otherwise
     */
    public boolean isElementOfModel(Event param){
        for(Event event : model.getData()){
            if(event.isSame(param)) return true;
        }
        return false;
    }

    /**
     * Calls model's appropriate function
     * 
     * @return the number of Events deletes
     */
    public int deletePastEventsFromModel(){
        return model.deletePastEvents();
    }

    /**
     * Calls model's appropriate function with the given parameters
     * 
     * @param param the Event that will be deleted
     */
    public void deleteFromModel(Event param){
        model.delete(param);
    }

    /**
     * Sorts model based on Event's compareTo method
     */
    public void sortData(){
        model.getData().sort(Event::compareTo);
    }

    /**
     * 
     * @param name the program checks each Event if it contains this String or not
     * @return all the Events that contain given String in the form of a List
     */
    public List<Event> nameCompare(String name){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getName().contains(name)) returnData.add(event);
        }
        return returnData;
    }

    /**
     * 
     * @param place the program checks each Event if it contains this String or not
     * @return all the Events that contain given String in the form of a List
     */
    public List<Event> placeCompare(String place){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getPlace().contains(place)) returnData.add(event);
        }
        return returnData;
    }

    /**
     * 
     * @param comment the program checks each Event if it contains this String or not
     * @return all the Events that contain given String in the form of a List
     */
    public List<Event> commentCompare(String comment){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getComment().contains(comment)) returnData.add(event);
        }
        return returnData;
    }

    /**
     * 
     * @param param an Event List that will be converted into a ShownEvent List
     * @return the ShownEvent List
     */
    public List<ShownEvent> toShownParameter(List<Event> param){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : param){
            returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    /**
     * 
     * @return a ShownEvent List that contains the same information as the current data
     */
    public List<ShownEvent> toShown(){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    /**
     * 
     * @param param1 a Date that the comparation will be based on
     * @param param2 another Date that the comparation will be based on
     * @return a List of ShownEvents that are running between the two Date values given as parameters
     */
    public List<ShownEvent> toShownBetweenDates(Date param1, Date param2){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.isActiveBetween(param1, param2)) returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    /**
     * 
     * @param year the year values as int
     * @param month the month value as int
     * @return the last day of the month represented by the given parameters
     */
    public int getMaxDay(int year, int month){
        if((month == 4 || month == 6 || month == 9 || month == 11) ) return 30;
        else if(month == 2 && Year.of(year).isLeap()) return 29;
        else if (month == 2) return 28;
        else return 31;
    }
    
    /**
     * Checks if the given Date values are valid, if the start is before the end, if the end is after current time, if the Strings contain enters and if the new Event is already a part of the stored data
     * If any of these are not ideal shows an error popup message
     * Otherwise adds the new Event to the stored data
     * 
     * @param start start Date of the new Event
     * @param end end Date of the new Event
     * @param name name of the new Event
     * @param place place of the new Event
     * @param comment comment given to the new Event
     */
    public void addEventUI(Date start, Date end, String name, String place, String comment){
        Event newEvent = new Event(name, place, comment, start, end);
        Date now = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
        if(name.contains("\n") || place.contains("\n") || comment.contains("\n")) JOptionPane.showMessageDialog(null, "A név, helyszín, illetve a megjegyzés mezőkbe nem kerülhet enter!");
        else if(now.compareTo(end) != -1) JOptionPane.showMessageDialog(null, "Már lejárt dátumú esemény nem kerülhet felvételre!");
        else if(!start.isValid()) JOptionPane.showMessageDialog(null, "Az esemény kezdete nem létező dátumot kíván beállítani!");
        else if(!end.isValid()) JOptionPane.showMessageDialog(null, "Az esemény vége nem létező dátumot kíván beállítani!");
        else if(end.compareTo(start) != 1) JOptionPane.showMessageDialog(null, "Nem lehet előbb az esemény befejezése, mint a kezdete!");
        else if(isElementOfModel(newEvent)) JOptionPane.showMessageDialog(null, "A felvenni kívánt esemény már része az adatbázisnak!");
        else addEventToModel(newEvent);
    }

    /**
     * Converts the parameter into a table, then adds it to a JScrollPane, sets some graphical parameters
     * 
     * @param data the list that the table will consist of
     * @return a JSCrollPane that contains the required table as well
     */
    public JScrollPane toScrollableTable(List<ShownEvent> data){
        String[] columns = {"Név", "Helyszín", "Megjegyzés", "Kezdő időpont", "Befejezési időpont"};
        Object[][] tableData = new Object[data.size()][5];
        for(int i = 0; i < data.size(); i++){
            ShownEvent current = data.get(i);
            tableData[i][0] = current.getName();
            tableData[i][1] = current.getPlace();
            tableData[i][2] = current.getComment();
            tableData[i][3] = current.getStart();
            tableData[i][4] = current.getEnd();
        }
        JTable table = new JTable(tableData, columns);
        table.setEnabled(false);
        TableColumnModel cModel = table.getColumnModel();
        cModel.getColumn(0).setPreferredWidth(320);
        cModel.getColumn(1).setPreferredWidth(120);
        cModel.getColumn(2).setPreferredWidth(320);
        cModel.getColumn(3).setPreferredWidth(120);
        cModel.getColumn(4).setPreferredWidth(120);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1000,600));
        return scroll;
    }

    /**
     * Creates a JOptionPane with JRadioButtons that each represent an element of the correct List
     * Gets user input as to which the user wants to modify/delete
     * If not the OK option is selected returns invalid value
     * Else returns the chosen index
     * 
     * @param correct a List of Events that have to be shown on the window
     * @return the value that has been chosen (index of the chosen Event in the correct List)
     */
    public int selectWindow(List<Event> correct){
        if(correct.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nincs ilyen esemény!");
            return -1;
        }
        List<ShownEvent> sCorrectName = toShownParameter(correct);
        String[] objects = new String[sCorrectName.size()];
        for(int i = 0; i < sCorrectName.size(); i++){
            objects[i] = sCorrectName.get(i).toString();
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] buttons = new JRadioButton[objects.length];
        for(int i = 0; i < objects.length; i++){
            buttons[i] = new JRadioButton(objects[i]);
            buttonGroup.add(buttons[i]);
            panel.add(buttons[i]);
        }
        buttons[0].setSelected(true);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setPreferredSize(new Dimension(1000, 600));
        int result = JOptionPane.showConfirmDialog(null, scroll, "Megtalált események (név; helyszín; megjegyzés; kezdet; befejezés)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        int idx = -1;
        if(result == JOptionPane.OK_OPTION){
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].isSelected()){
                    idx = i;
                    break;
                }
            }
        }
        return idx;
    }

    /**
     * If selectWindow returns with a valid value a popup window is shown to get validation on the deletion of the chosen Event
     * If yes option is chosen the Event will get deleted and the new data will be written into a file, otherwise nothing happens
     * 
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param correct a List of Events that have to be given to selectWindow as a parameter
     */
    public void deleteSearched(int runNum, List<Event> correct){
        int idx = selectWindow(correct);
        if(idx != -1){
            int yesNo = JOptionPane.showConfirmDialog(null, "Biztosan törölni szeretnéd a kijelölt elemet?", "Ellenőrzés" ,JOptionPane.YES_NO_OPTION);
            if(yesNo == JOptionPane.YES_OPTION){
                deleteFromModel(correct.get(idx));
                JOptionPane.showMessageDialog(null, "Esemény törölve!");
                writeModel("src/data.ics", runNum); //Safety save as stated in specification
            }
        }
    }

    /**
     * Creates a popup window with a JTextField, where input can be written
     * If yes option is chosen returns the given String, else returns an ivalid one
     * 
     * @param message message shown on the window
     * @return the String that the user has given
     */
    public String getInput(String message) throws IOException {
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(50);
        panel.add(textField);
        int yesNo = JOptionPane.showConfirmDialog(null, panel, "A módosítás utáni " + message, JOptionPane.YES_NO_OPTION);
        if(yesNo == JOptionPane.YES_OPTION) return textField.getText();
        throw new IOException();
    }

    /**
     * Gets user input on the fields selected and if yes option is selected modifies the appriopriate Event and writes the new stored data into a file 
     * 
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param correct a List of Events that have to be given to selectWindow as a parameter
     * @param modifiable which checkboxes have been selected
     */
    public void modifySearched(int runNum, List<Event> correct, boolean[] modifiable){
        int idx = selectWindow(correct);
        boolean modified = false;
        if(idx != -1){
            if(modifiable[0]){
                try{
                    String name = getInput("név");
                    model.modifyName(correct.get(idx), name);
                    modified = true;
                }catch(IOException exception) {/* No reason to do anything */}
            }
            if(modifiable[1]){
                try{
                    String place = getInput("helyszín");
                    model.modifyPlace(correct.get(idx), place);
                    modified = true;
                }catch(IOException exception) {/* No reason to do anything */}
            }
            if(modifiable[2]){
                try{
                    String comment = getInput("megjegyzés");
                    model.modifyComment(correct.get(idx), comment);
                    modified = true;
                }catch(IOException exception) {/* No reason to do anything */}
            }
            if(modified){
                JOptionPane.showMessageDialog(null, "Esemény módosítva!");
                writeModel("src/data.ics", runNum); //Safety save as stated in specification
            }
        }
    }

    /**
     * Calls modifySearched with the appropriate parameters
     * 
     * @param name String that the correct parameter of modifySearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param modifiable which checkboxes have been selected
     */
    public void modifyBasedOnName(String name, int runNum, boolean[] modifiable){
        modifySearched(runNum, nameCompare(name), modifiable);
    }

    /**
     * Calls modifySearched with the appropriate parameters
     * 
     * @param place String that the correct parameter of modifySearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param modifiable which checkboxes have been selected
     */
    public void modifyBasedOnPlace(String place, int runNum, boolean[] modifiable){
        modifySearched(runNum, placeCompare(place), modifiable);
    }

    /**
     * Calls modifySearched with the appropriate parameters
     * 
     * @param comment String that the correct parameter of modifySearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     * @param modifiable which checkboxes have been selected
     */
    public void modifyBasedOnComment(String comment, int runNum, boolean[] modifiable){
        modifySearched(runNum, commentCompare(comment), modifiable);
    }

    /**
     * Calls deleteSearched with the appropriate parameters
     * 
     * @param name String that the correct parameter of deleteSearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     */
    public void deleteBasedOnName(String name, int runNum){
        deleteSearched(runNum, nameCompare(name));
    }

    /**
     * Calls deleteSearched with the appropriate parameters
     * 
     * @param place String that the correct parameter of deleteSearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     */
    public void deleteBasedOnPlace(String place, int runNum){
        deleteSearched(runNum, placeCompare(place));
    }

    /**
     * Calls deleteSearched with the appropriate parameters
     * 
     * @param comment String that the correct parameter of deleteSearched will be calculated with
     * @param runNum is used in order to create uniqe IDs when data is saved
     */
    public void deleteBasedOnComment(String comment, int runNum){
        deleteSearched(runNum, commentCompare(comment));
    }
}
