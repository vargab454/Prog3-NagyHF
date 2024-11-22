package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {

    private AddMenu addMenu;
    private SearchMenu searchMenu;
    private ListMenu listMenu;
    private Controller controller;
    private int runNum;

    public Menu(int r){
        super("Határidőnapló");
        addMenu = new AddMenu(this, r);
        searchMenu = new SearchMenu(this, r);
        listMenu = new ListMenu(this);
        controller = new Controller();
        runNum = r;
        JButton addEvent = new JButton("Esemény felvétele");
        addEvent.setPreferredSize(new Dimension(300, 50));
        addEvent.addActionListener(new AddListener());
        JButton modifyEvent = new JButton("Esemény módosítása");
        modifyEvent.setPreferredSize(new Dimension(400, 50));
        JButton deleteEvent = new JButton("Esemény törlése");
        deleteEvent.setPreferredSize(new Dimension(400, 50));
        JButton searchEvent = new JButton("Esemény keresése");
        searchEvent.setPreferredSize(new Dimension(400, 50));
        searchEvent.addActionListener(new SearchListener());
        JButton listEvent = new JButton("Események listázása");
        listEvent.setPreferredSize(new Dimension(400, 50));
        listEvent.addActionListener(new ListListener());
        JButton exit = new JButton("Kilépés");
        exit.setPreferredSize(new Dimension(400, 50));
        exit.addActionListener(new ExitListener());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weighty = 0.5;
        panel.add(addEvent, constraint);
        constraint.gridy = 1;
        panel.add(modifyEvent, constraint);
        constraint.gridy = 2;
        panel.add(deleteEvent, constraint);
        constraint.gridy = 3;
        panel.add(searchEvent, constraint);
        constraint.gridy = 4;
        panel.add(listEvent, constraint);
        constraint.gridy = 5;
        panel.add(exit, constraint);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        add(panel);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            addMenu.start();
        }
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            searchMenu.start();
        }
    }

    private class ListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            listMenu.start();
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            controller.writeModel("src/data.ics", runNum);
            System.exit(0);
        }
    }
}