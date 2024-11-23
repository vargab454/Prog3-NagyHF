package code;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Year;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ListMenu extends JFrame {
    
    private Menu mainMenu;
    private Controller controller;

    private JComboBox<Integer> year;
    private JComboBox<Integer> month;
    private JComboBox<Integer> day;

    public ListMenu(Menu m){
        super("Határidőnapló - Események listázása");
        mainMenu = m;
        controller = new Controller();
        JTextField yearStater = new JTextField("Keresett év:");
        yearStater.setEditable(false);
        yearStater.setBorder(null);
        yearStater.setHorizontalAlignment(SwingConstants.CENTER);
        Integer[] years = new Integer[20];
        for(int i = 0; i < 20; i++) years[i] = LocalDate.now().getYear() + i;
        year = new JComboBox<>(years);
        JTextField monthStater = new JTextField("Keresett hónap:");
        monthStater.setEditable(false);
        monthStater.setBorder(null);
        monthStater.setHorizontalAlignment(SwingConstants.CENTER);
        Integer[] months = new Integer[12];
        for(int i = 0; i < 12; i++) months[i] = i + 1;
        month = new JComboBox<>(months);
        JTextField dayStater = new JTextField("Keresett nap:");
        dayStater.setEditable(false);
        dayStater.setBorder(null);
        dayStater.setHorizontalAlignment(SwingConstants.CENTER);
        Integer[] days = new Integer[31];
        for(int i = 0; i < 31; i++) days[i] = i + 1;
        day = new JComboBox<>(days);
        JButton searchDay = new JButton("Adott nap listázása");
        searchDay.addActionListener(new SearchDayListener());
        searchDay.setPreferredSize(new Dimension(200, 40));
        JButton searchWeek = new JButton("Adott hét listázása");
        searchWeek.addActionListener(new SearchWeekListener());
        searchWeek.setPreferredSize(new Dimension(200, 40));
        JButton searchMonth = new JButton("Adott hónap listázása");
        searchMonth.setPreferredSize(new Dimension(200, 40));
        searchMonth.addActionListener(new SearchMonthListener());
        JButton back = new JButton("Vissza");
        back.setPreferredSize(new Dimension(200, 40));
        back.addActionListener(new ExitListener());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(5, 5, 5, 5);
        panel.add(yearStater, constraint);
        constraint.gridx = 1;
        panel.add(year, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(monthStater, constraint);
        constraint.gridx = 1;
        panel.add(month, constraint);
        constraint.gridx = 0;
        constraint.gridy = 2;
        panel.add(dayStater, constraint);
        constraint.gridx = 1;
        panel.add(day, constraint);
        constraint.ipady = 30;
        constraint.gridx = 0;
        constraint.gridy = 3;
        panel.add(searchDay, constraint);
        constraint.gridx = 1;
        panel.add(searchWeek, constraint);
        constraint.gridx = 0;
        constraint.gridy = 4;
        panel.add(searchMonth, constraint);
        constraint.gridx = 1;
        panel.add(back, constraint);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        add(panel);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    public int getMaxDay(int year, int month){
        if((month == 4 || month == 6 || month == 9 || month == 11) ) return 31;
        else if(month == 2 && Year.of(year).isLeap()) return 29;
        else if (month == 2 && !Year.of(year).isLeap()) return 28;
        else return 30;
    }

    private class SearchDayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            int yearParam = (int) year.getSelectedItem();
            int monthParam = (int) month.getSelectedItem();
            int dayParam = (int) day.getSelectedItem();
            Date date1 = new Date(yearParam, monthParam, dayParam, 0, 0);
            if(!date1.isValid()) { JOptionPane.showMessageDialog(null, "A kiválasztott dátum nem létezik!"); return; }
            Date date2 = new Date(yearParam, monthParam, dayParam, 23, 59);
            controller.sortData();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownBetweenDates(date1, date2)), "A kívánt intervallumba eső események", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SearchWeekListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            int yearParam = (int) year.getSelectedItem();
            int monthParam = (int) month.getSelectedItem();
            int dayParam = (int) day.getSelectedItem();
            Date givenDate = new Date(yearParam, monthParam, dayParam, 0, 0);
            if(!givenDate.isValid()) { JOptionPane.showMessageDialog(null, "A beállított dátum nem létezik!"); return; }
            LocalDate given = LocalDate.of(yearParam, monthParam, dayParam);
            LocalDate end = given;
            while(end.getDayOfWeek() != DayOfWeek.SUNDAY) end = end.plusDays(1);
            LocalDate start = given;
            while(start.getDayOfWeek() != DayOfWeek.MONDAY) start = start.minusDays(1);
            Date date1 = new Date(start.getYear(), start.getMonthValue(), start.getDayOfMonth(), 0, 0);
            Date date2 = new Date(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 23, 59);
            controller.sortData();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownBetweenDates(date1, date2)), "A keresett intervallumba eső események", JOptionPane.INFORMATION_MESSAGE);   
        }
    }

    private class SearchMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            int yearParam = (int) year.getSelectedItem();
            int monthParam = (int) month.getSelectedItem();
            Date givenDate = new Date(yearParam, monthParam, (int) day.getSelectedItem(), 0, 0);
            if(!givenDate.isValid()) { JOptionPane.showMessageDialog(null, "A beállított dátum nem létezik!"); return; }
            Date date1 = new Date(yearParam, monthParam, 1, 0, 0);
            Date date2 = new Date(yearParam, monthParam, getMaxDay(yearParam, monthParam), 0, 0);
            controller.sortData();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownBetweenDates(date1, date2)), "A keresett intervallumba eső események", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            mainMenu.start();
        }
    }
}
