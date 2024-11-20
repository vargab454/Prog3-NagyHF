import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {

    private AddMenu addMenu;
    private View view;
    private int runNum;

    public Menu(int r){
        super("Határidőnapló");
        addMenu = new AddMenu(this);
        view = new View();
        runNum = r;
        JButton addEvent = new JButton("Esemény felvétele");
        addEvent.setPreferredSize(new Dimension(350, 50));
        addEvent.addActionListener(new AddListener());
        JButton modifyEvent = new JButton("Esemény módosítása");
        modifyEvent.setPreferredSize(new Dimension(350, 50));
        JButton deleteEvent = new JButton("Esemény törlése");
        deleteEvent.setPreferredSize(new Dimension(350, 50));
        JButton searchEvent = new JButton("Esemény keresése");
        searchEvent.setPreferredSize(new Dimension(350, 50));
        JButton listEvent = new JButton("Események listázása");
        listEvent.setPreferredSize(new Dimension(350, 50));
        JButton exit = new JButton("Kilépés");
        exit.setPreferredSize(new Dimension(350, 50));
        exit.addActionListener(new ExitListener());
        JPanel panel = new JPanel();
        panel.add(addEvent, BorderLayout.NORTH);
        panel.add(modifyEvent, BorderLayout.NORTH);
        panel.add(deleteEvent, BorderLayout.NORTH);
        panel.add(searchEvent, BorderLayout.NORTH);
        panel.add(listEvent, BorderLayout.NORTH);
        panel.add(exit, BorderLayout.NORTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        add(panel, BorderLayout.CENTER);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            addMenu.start();
            close();
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            view.save("data.ics", runNum);
            System.exit(0);
        }
    }
}