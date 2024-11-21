package code;

public class Main {

    public static void main(String[] args){
        Controller controller = new Controller();
        int runNum = controller.read("src/data.ics");
        Menu menu = new Menu(runNum);
        menu.start();
    }
    
}
