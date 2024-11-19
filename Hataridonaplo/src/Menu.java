import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JFrame {

    private JButton addEvent;
    private JButton modifyEvent;
    private JButton deleteEvent;
    private JButton searchEvent;
    private JButton listEvent;
    private JButton exit;

    public Menu(){
        super("Határidőnapló");
        addEvent = new JButton("Esemény felvétele");
        addEvent.setPreferredSize(new Dimension(350, 50));
        modifyEvent = new JButton("Esemény módosítása");
        modifyEvent.setPreferredSize(new Dimension(350, 50));
        deleteEvent = new JButton("Esemény törlése");
        deleteEvent.setPreferredSize(new Dimension(350, 50));
        searchEvent = new JButton("Esemény keresése");
        searchEvent.setPreferredSize(new Dimension(350, 50));
        listEvent = new JButton("Események listázása");
        listEvent.setPreferredSize(new Dimension(350, 50));
        exit = new JButton("Kilépés");
        exit.setPreferredSize(new Dimension(350, 50));
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
}