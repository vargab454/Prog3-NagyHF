package code;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Controller implements Serializable {
    
    Model model;

    public Controller(){ model = new Model(); }

    public void writeModel(String filename, int runNum){ model.write(filename, runNum); }

    public int readModel(String filename){ return model.read(filename); }

    public int getModelSize(){ return model.getData().size(); }

    public void addEventToModel(Event param){ model.addEvent(param); }

    public boolean isElementOfModel(Event param){
        for(Event event : model.getData()){
            if(event.isSame(param)) return true;
        }
        return false;
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

    public List<ShownEvent> toShownBetweenDates(Date param1, Date param2){
        List<ShownEvent> returnData = new LinkedList<>();
        for(Event event : model.getData()){
            if(event.isActiveBetween(param1, param2)) returnData.add(event.toShownEvent());
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
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1000,800));
        return scroll;
    }

    public boolean isEmpty() { return getModelSize() == 0; }
    
}
