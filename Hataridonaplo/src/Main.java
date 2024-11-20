public class Main {

    public static void main(String[] args){
        Controller view = new Controller();
        int runNum = view.load("data.ics");
        Menu menu = new Menu(runNum);
        menu.start();
    }
    
}
