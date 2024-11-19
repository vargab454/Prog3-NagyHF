import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class AddMenu extends JFrame {

    private Menu mainMenu;

    public AddMenu(Menu m){
        super("Határidőnapló - Eseményfelvétel");
        mainMenu = m;
        JButton add = new JButton("Hozzáadás");
        JButton back = new JButton("Vissza");
        back.addActionListener(new ExitListener());
        JTextField nameStater = new JTextField("Esemény neve:");
        nameStater.setEditable(false);
        nameStater.setBorder(null);
        nameStater.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextArea name = new JTextArea();
        name.setLineWrap(true);
        name.setWrapStyleWord(true);
        JTextField placeStater = new JTextField("Esemény helyszíne:");
        placeStater.setEditable(false);
        placeStater.setBorder(null);
        placeStater.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextArea place = new JTextArea();
        place.setLineWrap(true);
        place.setWrapStyleWord(true);
        JTextField commentStater = new JTextField("Megjegyzés:");
        commentStater.setEditable(false);
        commentStater.setBorder(null);
        commentStater.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextArea comment = new JTextArea();
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
        JComboBox<Integer> startYear = new JComboBox<>(years);
        JComboBox<Integer> endYear = new JComboBox<>(years);
        Integer[] months = new Integer[12];
        for(int i = 0; i < 12; i++) months[i] = i + 1;
        JComboBox<Integer> startMonth = new JComboBox<>(months);
        JComboBox<Integer> endMonth = new JComboBox<>(months);
        Integer[] days = new Integer[31];
        for(int i = 0; i < 31; i++) days[i] = i + 1;
        JComboBox<Integer> startDay = new JComboBox<>(days);
        JComboBox<Integer> endDay = new JComboBox<>(days);
        Integer[] hours = new Integer[24];
        for(int i = 0; i < 24; i++) hours[i] = i;
        JComboBox<Integer> startHour = new JComboBox<>(hours);
        JComboBox<Integer> endHour = new JComboBox<>(hours);
        Integer[] minutes = new Integer[60];
        for(int i = 0; i < 60; i++) minutes[i] = i;
        JComboBox<Integer> startMinute = new JComboBox<>(minutes);
        JComboBox<Integer> endMinute = new JComboBox<>(minutes);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.ipady = 50;
        constraint.gridy = 0;
        constraint.weightx = 0.5;
        constraint.weighty = 0.5;
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
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
    }

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    private class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            close();
            mainMenu.start();
        }
    }
}
