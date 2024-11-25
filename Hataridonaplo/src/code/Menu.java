package code;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    private AddMenu addMenu;
    private ModifyMenu modifyMenu;
    private DeleteMenu deleteMenu;
    private SearchMenu searchMenu;
    private ListMenu listMenu;
    private Controller controller;
    private int runNum;

    public Menu(int r){
        super("Határidőnapló");
        runNum = r;
        addMenu = new AddMenu(this, runNum);
        modifyMenu = new ModifyMenu(this, runNum);
        deleteMenu = new DeleteMenu(this, runNum);
        searchMenu = new SearchMenu(this);
        listMenu = new ListMenu(this);
        controller = new Controller();
        JMenu jMenu = new JMenu("Funkciók");
        JMenuItem addEvent = new JMenuItem("Esemény felvétele");
        jMenu.add(addEvent);
        addEvent.setActionCommand("add");
        addEvent.addActionListener(new MenuActionListener());
        JMenuItem modifyEvent = new JMenuItem("Esemény módosítása");
        jMenu.add(modifyEvent);
        modifyEvent.setActionCommand("modify");
        modifyEvent.addActionListener(new MenuActionListener());
        JMenuItem deleteEvent = new JMenuItem("Esemény törlése");
        jMenu.add(deleteEvent);
        deleteEvent.setActionCommand("delete");
        deleteEvent.addActionListener(new MenuActionListener());
        JMenuItem searchEvent = new JMenuItem("Esemény keresése");
        jMenu.add(searchEvent);
        searchEvent.addActionListener(new MenuActionListener());
        searchEvent.setActionCommand("search");
        JMenuItem listEvent = new JMenuItem("Események listázása");
        jMenu.add(listEvent);
        listEvent.addActionListener(new MenuActionListener());
        listEvent.setActionCommand("list");
        JMenu exitMenu = new JMenu("Leállítás");
        JMenuItem exit = new JMenuItem("Kilépés");
        exitMenu.add(exit);
        exit.addActionListener(new MenuActionListener());
        exit.setActionCommand("exit");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(jMenu);
        menuBar.add(exitMenu);
        setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        ImageIcon image = new ImageIcon("src/icon.png");
        JLabel label = new JLabel(image);
        panel.add(label);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        add(panel);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            switch (event.getActionCommand()) {
                case "add":
                    close();
                    addMenu.start();
                    break;
                case "modify":
                    close();
                    modifyMenu.start();
                    break;
                case "delete":
                    close();
                    deleteMenu.start();
                    break;
                case "search":
                    close();
                    searchMenu.start();
                    break;
                case "list":
                    close();
                    listMenu.start();
                    break;
                case "exit":
                    controller.writeModel("src/data.ics", runNum);
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}