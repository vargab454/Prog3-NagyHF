package code;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DeleteMenu extends JFrame {

    private Menu mainMenu;
    private Controller controller;
    private int runNum;

    private JTextArea searchValue;

    public DeleteMenu(Menu m, int r){
        super("Határidőnapló - Esemény keresése");
        mainMenu = m;
        runNum = r;
        controller = new Controller();
        JTextField searchStater = new JTextField("Keresett érték:");
        searchStater.setEditable(false);
        searchStater.setBorder(null);
        searchStater.setHorizontalAlignment(SwingConstants.CENTER);
        searchStater.setPreferredSize(new Dimension(100, 100));
        searchValue = new JTextArea();
        searchValue.setLineWrap(true);
        searchValue.setWrapStyleWord(true);
        searchValue.setPreferredSize(new Dimension(300, 100));
        JButton searchName = new JButton("Keresés név alapján, majd törlés");
        searchName.setPreferredSize(new Dimension(200, 100));
        JButton searchPlace = new JButton("Keresés helyszín alapján, majd törlés");
        searchPlace.setPreferredSize(new Dimension(200, 100));
        JButton searchComment = new JButton("Keresés megjegyzés alapján, törlés");
        searchComment.setPreferredSize(new Dimension(200, 100));
        JButton deletePast = new JButton("Lejárt határidők törlése");
        deletePast.addActionListener(new DeleteListener());
        JButton back = new JButton("Vissza");
        back.setPreferredSize(new Dimension(400, 100));
        back.addActionListener(new ExitListener());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weighty = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(5, 5, 5, 5);
        panel.add(searchStater, constraint);
        constraint.gridwidth = 1;
        constraint.gridx = 1;
        panel.add(searchValue, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(searchName, constraint);
        constraint.gridx = 1;
        panel.add(searchPlace, constraint);
        constraint.gridx = 0;
        constraint.gridy = 2;
        panel.add(searchComment, constraint);
        constraint.gridx = 1;
        panel.add(deletePast, constraint);
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 2;
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

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            int deletedNum = controller.deletePastEventsFromModel();
            if(deletedNum == 0) JOptionPane.showMessageDialog(null, "Nincs lejárt esemény, amit törölni lehetne!");
            else{
                JOptionPane.showMessageDialog(null, deletedNum + " darab lejárt esemény került törlésre!");
                controller.writeModel("src/data.ics", runNum); //Safety save as stated in specification
            }
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
