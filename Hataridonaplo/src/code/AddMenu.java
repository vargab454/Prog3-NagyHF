package code;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;

public class AddMenu extends JFrame {

    private Menu mainMenu;
    private Controller controller;
    private int runNum;

    private JTextArea name;
    private JTextArea place;
    private JTextArea comment;
    private JComboBox<Integer> startYear;
    private JComboBox<Integer> endYear;
    private JComboBox<Integer> startMonth;
    private JComboBox<Integer> endMonth;
    private JComboBox<Integer> startDay;
    private JComboBox<Integer> endDay;
    private JComboBox<Integer> startHour;
    private JComboBox<Integer> endHour;
    private JComboBox<Integer> startMinute;
    private JComboBox<Integer> endMinute;

    /**
     * 
     * @param m main menu, in order to be able to make it visible
     * @param r runNum, this will be used in order to create uniqe IDs when data is saved
     * 
     * Creates the add event menu
     */
    public AddMenu(Menu m, int r){
        super("Határidőnapló - Esemény felvétele");
        mainMenu = m;
        runNum = r;
        controller = new Controller();
        JButton add = new JButton("Hozzáadás");
        add.addActionListener(new AddActionListener());
        add.setActionCommand("add");
        JButton back = new JButton("Vissza");
        back.addActionListener(new AddActionListener());
        back.setActionCommand("back");
        JTextField nameStater = new JTextField("Esemény neve:");
        nameStater.setEditable(false);
        nameStater.setBorder(null);
        nameStater.setHorizontalAlignment(SwingConstants.RIGHT);
        name = new JTextArea();
        name.setLineWrap(true);
        name.setWrapStyleWord(true);
        JTextField placeStater = new JTextField("Esemény helyszíne:");
        placeStater.setEditable(false);
        placeStater.setBorder(null);
        placeStater.setHorizontalAlignment(SwingConstants.RIGHT);
        place = new JTextArea();
        place.setLineWrap(true);
        place.setWrapStyleWord(true);
        JTextField commentStater = new JTextField("Megjegyzés:");
        commentStater.setEditable(false);
        commentStater.setBorder(null);
        commentStater.setHorizontalAlignment(SwingConstants.RIGHT);
        comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        JTextField startStater = new JTextField("Esemény kezdete:");
        startStater.setEditable(false);
        startStater.setBorder(null);
        startStater.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField endStater = new JTextField("Esemény vége:");
        endStater.setEditable(false);
        endStater.setBorder(null);
        endStater.setHorizontalAlignment(SwingConstants.RIGHT);
        Integer[] years = new Integer[20];
        for(int i = 0; i < 20; i++) years[i] = LocalDate.now().getYear() + i;
        startYear = new JComboBox<>(years);
        endYear = new JComboBox<>(years);
        Integer[] months = new Integer[12];
        for(int i = 0; i < 12; i++) months[i] = i + 1;
        startMonth = new JComboBox<>(months);
        endMonth = new JComboBox<>(months);
        Integer[] days = new Integer[31];
        for(int i = 0; i < 31; i++) days[i] = i + 1;
        startDay = new JComboBox<>(days);
        endDay = new JComboBox<>(days);
        Integer[] hours = new Integer[24];
        for(int i = 0; i < 24; i++) hours[i] = i;
        startHour = new JComboBox<>(hours);
        endHour = new JComboBox<>(hours);
        Integer[] minutes = new Integer[60];
        for(int i = 0; i < 60; i++) minutes[i] = i;
        startMinute = new JComboBox<>(minutes);
        endMinute = new JComboBox<>(minutes);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.ipady = 50;
        constraint.gridy = 0;
        constraint.weightx = 0.5;
        constraint.weighty = 0.5;
        constraint.insets = new Insets(5, 5, 5, 5);
        panel.add(nameStater, constraint);
        constraint.gridx = 1;
        constraint.gridwidth = 5;
        panel.add(name, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        panel.add(placeStater, constraint);
        constraint.gridx = 1;
        constraint.gridwidth = 5;
        panel.add(place, constraint);
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        panel.add(commentStater, constraint);
        constraint.gridx = 1;
        constraint.gridwidth = 5;
        panel.add(comment, constraint);
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        constraint.ipady = 0;
        panel.add(startStater, constraint);
        constraint.gridx = 1;
        panel.add(startYear, constraint);
        constraint.gridx = 2;
        panel.add(startMonth, constraint);
        constraint.gridx = 3;
        panel.add(startDay, constraint);
        constraint.gridx = 4;
        panel.add(startHour, constraint);
        constraint.gridx = 5;
        panel.add(startMinute, constraint);
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.gridwidth = 1;
        constraint.ipady = 0;
        panel.add(endStater, constraint);
        constraint.gridx = 1;
        panel.add(endYear, constraint);
        constraint.gridx = 2;
        panel.add(endMonth, constraint);
        constraint.gridx = 3;
        panel.add(endDay, constraint);
        constraint.gridx = 4;
        panel.add(endHour, constraint);
        constraint.gridx = 5;
        panel.add(endMinute, constraint);
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.gridwidth = 3;
        panel.add(add, constraint);
        constraint.gridx = 3;
        panel.add(back, constraint);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        add(panel);
    }

    /**
     * Makes the add event menu visible
     */
    public void start() { setVisible(true); }

    /**
     * Makes the add event menu not visible
     */
    public void close() { setVisible(false); }

    /**
     * Handles user input -> add option: creates Dates from items that have been selected in the JComboBoxes, calls controller's appropriate functions
     *                    -> back option: makes the main menu visible, the add event menu not visible 
     */
    private class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            switch(event.getActionCommand()) {
                case "add":
                    Date start = new Date((int) startYear.getSelectedItem(), (int) startMonth.getSelectedItem(), (int) startDay.getSelectedItem(), (int) startHour.getSelectedItem(), (int) startMinute.getSelectedItem());
                    Date end = new Date((int) endYear.getSelectedItem(), (int) endMonth.getSelectedItem(), (int) endDay.getSelectedItem(), (int) endHour.getSelectedItem(), (int) endMinute.getSelectedItem());
                    controller.addEventUI(start, end, name.getText(), place.getText(), comment.getText());
                    controller.writeModel("src/data.ics", runNum); //Safety save as stated in specification
                    break;
                case "back":
                    close();
                    mainMenu.start();
                    break;
                default:
                    break;
            }
        }
    }
}
