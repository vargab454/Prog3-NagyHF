package code;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ModifyMenu extends JFrame {
    
    private Menu mainMenu;
    private Controller controller;
    private int runNum;

    private JTextArea searchValue;
    private JCheckBox modifyName;
    private JCheckBox modifyPlace;
    private JCheckBox modifyComment;

    /**
     * Creates the modify event menu
     * 
     * @param m main menu, in order to be able to make it visible
     * @param r runNum, this will be used in order to create uniqe IDs when data is saved
     */
    public ModifyMenu(Menu m, int r){
        super("Határidőnapló - Esemény módosítása");
        mainMenu = m;
        runNum = r;
        controller = new Controller();
        JTextField searchStater = new JTextField("Keresett érték:");
        searchStater.setEditable(false);
        searchStater.setBorder(null);
        searchStater.setHorizontalAlignment(SwingConstants.CENTER);
        searchValue = new JTextArea();
        searchValue.setLineWrap(true);
        searchValue.setWrapStyleWord(true);
        JButton searchName = new JButton("Név keresése, majd módosítás");
        searchName.addActionListener(new ModifyActionListener());
        searchName.setActionCommand("name");
        JButton searchPlace = new JButton("Helyszín keresése, majd módosítás");
        searchPlace.addActionListener(new ModifyActionListener());
        searchPlace.setActionCommand("place");
        JButton searchComment = new JButton("Megjegyzés keresése, módosítás");
        searchComment.addActionListener(new ModifyActionListener());
        searchComment.setActionCommand("comment");
        modifyName = new JCheckBox("Név módosítása");
        modifyPlace = new JCheckBox("Helyszín módosítása");
        modifyComment = new JCheckBox("Megjegyzés módosítása");
        JButton back = new JButton("Vissza");        
        back.setPreferredSize(new Dimension(400, 100));
        back.addActionListener(new ModifyActionListener());
        back.setActionCommand("back");
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
        panel.add(back, constraint);
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 2;
        constraint.anchor = GridBagConstraints.CENTER;
        constraint.fill = GridBagConstraints.NONE;
        constraint.weighty = 0.1;
        panel.add(modifyName, constraint);
        constraint.gridy = 4;
        panel.add(modifyPlace, constraint);
        constraint.gridy = 5;
        panel.add(modifyComment, constraint);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(700, 400, 500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        add(panel);
    }

    /**
     * Makes the modify event menu visible
     */
    public void start() { setVisible(true); }

    /**
     * Makes the modify event menu not visible
     */
    public void close() { setVisible(false); }

    /** 
     * If none of the JCheckBoxes have been selected returns true, otherwise returns false
    */
    public boolean noneSelectedBooleanValue(){ return !modifyName.isSelected() && !modifyPlace.isSelected() && !modifyComment.isSelected(); }

    /**
     * If none of the JCheckBoxes have been selected shows error message
     */
    public void noneSelected() { if(noneSelectedBooleanValue()) JOptionPane.showMessageDialog(null, "Egyik mező sincs kiválasztva módosításra!"); }

    /** 
     * Returns the selected JCheckBoxes in the form of a boolean array
    */
    public boolean[] getSelected() {
        boolean[] ret = new boolean[3];
        for(int i = 0; i < 3; i++) ret[i] = false;
        if(modifyName.isSelected()) ret[0] = true;
        if(modifyPlace.isSelected()) ret[1] = true;
        if(modifyComment.isSelected()) ret[2] = true;
        return ret;
    }

    /**
     * Handles user input -> name option: calls noneSelected, if any have been selected calls controller's appropriate function
     *                    -> place option: calls noneSelected, if any have been selected calls controller's appropriate function
     *                    -> comment option: calls noneSelected, if any have been selected calls controller's appropriate function
     *                    -> back option: makes the main menu visible, the modify event menu not visible 
     */
    private class ModifyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            switch (event.getActionCommand()) {
                case "name":
                    noneSelected();
                    if(!noneSelectedBooleanValue()) controller.modifyBasedOnName(searchValue.getText(), runNum, getSelected());
                    break;
                case "place":
                    noneSelected();
                    if(!noneSelectedBooleanValue()) controller.modifyBasedOnPlace(searchValue.getText(), runNum, getSelected());
                    break;
                case "comment":
                    noneSelected();
                    if(!noneSelectedBooleanValue()) controller.modifyBasedOnComment(searchValue.getText(), runNum, getSelected());
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
