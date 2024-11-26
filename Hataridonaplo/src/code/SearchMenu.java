package code;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class SearchMenu extends JFrame {

    private Menu mainMenu;
    private Controller controller;

    private JTextArea searchValue;

    /**
     * 
     * @param m main menu, in order to be able to make it visible
     * 
     * Creates the search event menu
     */
    public SearchMenu(Menu m){
        super("Határidőnapló - Esemény keresése");
        mainMenu = m;
        controller = new Controller();
        JTextField searchStater = new JTextField("Keresett érték:");
        searchStater.setEditable(false);
        searchStater.setBorder(null);
        searchStater.setHorizontalAlignment(SwingConstants.CENTER);
        searchStater.setPreferredSize(new Dimension(100, 60));
        searchValue = new JTextArea();
        searchValue.setLineWrap(true);
        searchValue.setWrapStyleWord(true);
        searchValue.setPreferredSize(new Dimension(300, 60));
        JButton searchName = new JButton("Keresés név alapján");
        searchName.addActionListener(new SearchActionListener());
        searchName.setActionCommand("name");
        searchName.setPreferredSize(new Dimension(400, 60));
        JButton searchPlace = new JButton("Keresés helyszín alapján");
        searchPlace.addActionListener(new SearchActionListener());
        searchPlace.setActionCommand("place");
        searchPlace.setPreferredSize(new Dimension(400, 60));
        JButton searchComment = new JButton("Keresés megjegyzés alapján");
        searchComment.addActionListener(new SearchActionListener());
        searchComment.setActionCommand("comment");
        searchComment.setPreferredSize(new Dimension(400, 60));
        JButton back = new JButton("Vissza");
        back.setPreferredSize(new Dimension(400, 60));
        back.addActionListener(new SearchActionListener());
        back.setActionCommand("back");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(searchStater, constraint);
        constraint.gridx = 1;
        panel.add(searchValue, constraint);
        constraint.gridwidth = 2;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(searchName, constraint);
        constraint.gridy = 2;
        panel.add(searchPlace, constraint);
        constraint.gridy = 3;
        panel.add(searchComment, constraint);
        constraint.gridy = 4;
        panel.add(back, constraint);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        add(panel);
    }

    /**
     * Makes the search event menu visible
     */
    public void start() { setVisible(true); }

    /**
     * Makes the search event menu not visible
     */
    public void close() { setVisible(false); }

    /**
     * Handles user input -> name option: creates a table with the correct events with calling controllr's appropriate function
     *                    -> place option: creates a table with the correct events with calling controllr's appropriate function
     *                    -> comment option: creates a table with the correct events with calling controllr's appropriate function
     *                    -> back option: makes the main menu visible, the search event menu not visible 
     */
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            switch(event.getActionCommand()){
                case "name":
                    JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownParameter(controller.nameCompare(searchValue.getText()))), "Azon események, melyek neve tartalmazza a '" + searchValue.getText() + "'részletet", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "place":
                JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownParameter(controller.placeCompare(searchValue.getText()))), "Azon események, melyek helyszíne tartalmazza a '" + searchValue.getText() + "' tagot", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "comment":
                    JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownParameter(controller.commentCompare(searchValue.getText()))), "Azon események, melyek megjegyzése tartalmazza a '" + searchValue.getText() + "' tagot", JOptionPane.INFORMATION_MESSAGE);
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
