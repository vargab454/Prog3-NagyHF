package code;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ListMenu extends JFrame {
    
    private Menu mainMenu;
    private Controller controller;

    private JComboBox year;
    private JComboBox month;
    private JComboBox day;

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
        searchDay.setPreferredSize(new Dimension(200, 40));
        JButton searchWeek = new JButton("Adott hét listázása");
        searchWeek.setPreferredSize(new Dimension(200, 40));
        JButton searchMonth = new JButton("Adott hónap listázása");
        searchMonth.setPreferredSize(new Dimension(200, 40));
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
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            mainMenu.start();
        }
    }
}
