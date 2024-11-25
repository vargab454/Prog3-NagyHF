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
        super("Határidőnapló - Esemény törlése");
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
        JButton searchName = new JButton("Keresés név alapján, majd törlés");
        searchName.addActionListener(new DeleteActionListener());
        searchName.setActionCommand("name");
        JButton searchPlace = new JButton("Keresés helyszín alapján, majd törlés");
        searchPlace.addActionListener(new DeleteActionListener());
        searchPlace.setActionCommand("place");
        JButton searchComment = new JButton("Keresés megjegyzés alapján, törlés");
        searchComment.addActionListener(new DeleteActionListener());
        searchComment.setActionCommand("comment");
        JButton deletePast = new JButton("Lejárt határidők törlése");
        deletePast.addActionListener(new DeleteActionListener());
        deletePast.setActionCommand("past");
        JButton back = new JButton("Vissza");
        back.addActionListener(new DeleteActionListener());
        back.setActionCommand("back");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 0.5;
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

    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            switch (event.getActionCommand()) {
                case "name":
                    controller.deleteBasedOnName(searchValue.getText(), runNum);
                    break;
                case "place":
                    controller.deleteBasedOnPlace(searchValue.getText(), runNum);
                    break;
                case "comment":
                    controller.deleteBasedOnComment(searchValue.getText(), runNum);
                    break;
                case "past":
                    int deletedNum = controller.deletePastEventsFromModel();
                    if(deletedNum == 0) JOptionPane.showMessageDialog(null, "Nincs lejárt esemény, amit törölni lehetne!");
                    else{
                        JOptionPane.showMessageDialog(null, deletedNum + " darab lejárt esemény került törlésre!");
                        controller.writeModel("src/data.ics", runNum); //Safety save as stated in specification
                    }
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
