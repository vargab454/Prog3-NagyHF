public class Main {

    public static void main(String[] args){
        View view = new View();
        int runNum = view.load("data.ics");
        Menu menu = new Menu(runNum);
        menu.start();
    }
    
}
