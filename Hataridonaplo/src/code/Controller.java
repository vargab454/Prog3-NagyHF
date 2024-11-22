package code;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Controller {
    
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

    public JScrollPane toScrollableTable(List<ShownEvent> data){
        String[] columns = {"Név", "Helyszín", "Megjegyzés", "Kezdő időpont", "Befejezési időpont"};
        Object[][] tableData = new Object[getModelSize()][5];
        for(int i = 0; i < getModelSize(); i++){
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
