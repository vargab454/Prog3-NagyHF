package code;

import java.awt.Dimension;

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

    public void writeModel(String filename, int runNum){ model.write(filename, runNum); }

    public int readModel(String filename){ return model.read(filename); }

    public int getModelSize(){ return model.getData().size(); }
    
    public boolean isEmpty() { return getModelSize() == 0; }

    public void addEventToModel(Event param){ model.addEvent(param); }

    public void clearModel() { model.clear(); }

    public boolean isElementOfModel(Event param){
        for(Event event : model.getData()){
            if(event.isSame(param)) return true;
        }
        return false;
    }

    public int deletePastEventsFromModel(){
        return model.deletePastEvents();
    }

    public void deleteFromModel(Event param){
        model.delete(param);
    }

    public void sortData(){
        model.getData().sort(Event::compareTo);
    }

    public List<Event> nameCompare(String name){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getName().contains(name)) returnData.add(event);
        }
        return returnData;
    }

    public List<Event> placeCompare(String place){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getPlace().contains(place)) returnData.add(event);
        }
        return returnData;
    }

    public List<Event> commentCompare(String comment){
        List<Event> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.getComment().contains(comment)) returnData.add(event);
        }
        return returnData;
    }

    public List<ShownEvent> toShownParameter(List<Event> param){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : param){
            returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    public List<ShownEvent> toShown(){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    public List<ShownEvent> toShownNameCompare(String name){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            ShownEvent sEvent = event.toShownEvent();
            if(sEvent.getName().contains(name)) returnData.add(sEvent);
        }
        return returnData;
    }

    public List<ShownEvent> toShownPlaceCompare(String place){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            ShownEvent sEvent = event.toShownEvent();
            if(sEvent.getPlace().contains(place)) returnData.add(sEvent);
        }
        return returnData;
    }

    public List<ShownEvent> toShownCommentCompare(String comment){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            ShownEvent sEvent = event.toShownEvent();
            if(sEvent.getComment().contains(comment)) returnData.add(sEvent);
        }
        return returnData;
    }

    public List<ShownEvent> toShownBetweenDates(Date param1, Date param2){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.isActiveBetween(param1, param2)) returnData.add(event.toShownEvent());
        }
        return returnData;
    }

    public int getMaxDay(int year, int month){
        if((month == 4 || month == 6 || month == 9 || month == 11) ) return 30;
        else if(month == 2 && Year.of(year).isLeap()) return 29;
        else if (month == 2) return 28;
        else return 31;
    }
    
    public void addEventUI(Date start, Date end, String name, String place, String comment){
        Event newEvent = new Event(name, place, comment, start, end);
        Date now = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
        if(name.contains("\n") || place.contains("\n") || comment.contains("\n")) JOptionPane.showMessageDialog(null, "A név, helyszín, illetve a megjegyzés mezőkbe nem kerülhet enter!");
        else if(now.compareTo(start) != -1) JOptionPane.showMessageDialog(null, "Már lejárt dátumú esemény nem kerülhet felvételre!");
        else if(!start.isValid()) JOptionPane.showMessageDialog(null, "Az esemény kezdete nem létező dátumot kíván beállítani!");
        else if(!end.isValid()) JOptionPane.showMessageDialog(null, "Az esemény vége nem létező dátumot kíván beállítani!");
        else if(end.compareTo(start) != 1) JOptionPane.showMessageDialog(null, "Nem lehet előbb az esemény befejezése, mint a kezdete!");
        else if(isElementOfModel(newEvent)) JOptionPane.showMessageDialog(null, "A felvenni kívánt esemény már része az adatbázisnak!");
        else addEventToModel(newEvent);
    }

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
        scroll.setPreferredSize(new Dimension(1000,800));
        return scroll;
    }

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
        scroll.setPreferredSize(new Dimension(1000, 800));
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

    public String getInput(String message){
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(50);
        panel.add(textField);
        int yesNo = JOptionPane.showConfirmDialog(null, panel, "A módosítás utáni " + message, JOptionPane.YES_NO_OPTION);
        if(yesNo == JOptionPane.YES_OPTION) return textField.getText();
        return "InvalidInputGivenByUserFromUI";
    }

    public void modifySearched(int runNum, List<Event> correct, boolean[] modifiable){
        int idx = selectWindow(correct);
        boolean modified = false;
        if(idx != -1){
            if(modifiable[0]){
                String name = getInput("név");
                if(!name.equals("InvalidInputGivenByUserFromUI") && !name.contains("\n"))  { model.modifyName(correct.get(idx), name); modified = true; }
                else if(name.contains("\n")) JOptionPane.showMessageDialog(null, "Nem tartalmazhat entert a név!");
            }
            if(modifiable[1]){
                String place = getInput("helyszín");
                if(!place.equals("InvalidInputGivenByUserFromUI") && !place.contains("\n")) { model.modifyPlace(correct.get(idx), place); modified = true; }
                else if(place.contains("\n")) JOptionPane.showMessageDialog(null, "Nem tartalmazhat entert a helyszín!");
            }
            if(modifiable[2]){
                String comment = getInput("megjegyzés");
                if(!comment.equals("InvalidInputGivenByUserFromUI") && !comment.contains("\n")) { model.modifyComment(correct.get(idx), comment); modified = true; }
                else if(comment.contains("\n")) JOptionPane.showMessageDialog(null, "Nem tartalmazhat entert a megjegyzés!");
            }
            if(modified){
                JOptionPane.showMessageDialog(null, "Esemény módosítva!");
                writeModel("src/data.ics", runNum); //Safety save as stated in specification
            }
        }
    }

    public void modifyBasedOnName(String name, int runNum, boolean[] modifiable){
        modifySearched(runNum, nameCompare(name), modifiable);
    }

    public void modifyBasedOnPlace(String place, int runNum, boolean[] modifiable){
        modifySearched(runNum, placeCompare(place), modifiable);
    }

    public void modifyBasedOnComment(String comment, int runNum, boolean[] modifiable){
        modifySearched(runNum, commentCompare(comment), modifiable);
    }

    public void deleteBasedOnName(String name, int runNum){
        deleteSearched(runNum, nameCompare(name));
    }

    public void deleteBasedOnPlace(String place, int runNum){
        deleteSearched(runNum, placeCompare(place));
    }

    public void deleteBasedOnComment(String comment, int runNum){
        deleteSearched(runNum, commentCompare(comment));
    }
}
