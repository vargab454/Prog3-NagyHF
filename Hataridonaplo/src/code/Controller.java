package code;

public class Controller {
    
    Model model;

    public Controller(){ model = new Model(); }

    public void writeModel(String filename, int runNum){ model.write(filename, runNum); }

    public int readModel(String filename){ return model.read(filename); }

    public int getModelSize(){ return model.getSize(); }

    public void addEventToModel(Event param){ model.addEvent(param); }

    public boolean isElementOfModel(Event param){ return model.isElement(param); }

    public boolean isEmpty() { return model.getSize() == 0; }
    
}
