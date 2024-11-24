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
        searchName.addActionListener(new ModifyNameListener());
        JButton searchPlace = new JButton("Helyszín keresése, majd módosítás");
        searchPlace.addActionListener(new ModifyPlaceListener());
        JButton searchComment = new JButton("Megjegyzés keresése, módosítás");
        searchComment.addActionListener(new ModifyCommentListener());
        modifyName = new JCheckBox("Név módosítása");
        modifyPlace = new JCheckBox("Helyszín módosítása");
        modifyComment = new JCheckBox("Megjegyzés módosítása");
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

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }

    public void noneSelected() { if(!modifyName.isSelected() && !modifyPlace.isSelected() && !modifyComment.isSelected()) JOptionPane.showMessageDialog(null, "Egyik mező sincs kiválasztva módosításra!"); }

    public boolean noneSelectedBooleanValue(){ return !modifyName.isSelected() && !modifyPlace.isSelected() && !modifyComment.isSelected(); }

    public boolean[] getSelected() {
        boolean[] ret = new boolean[3];
        for(int i = 0; i < 3; i++) ret[i] = false;
        if(modifyName.isSelected()) ret[0] = true;
        if(modifyPlace.isSelected()) ret[1] = true;
        if(modifyComment.isSelected()) ret[2] = true;
        return ret;
    }

    private class ModifyNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            noneSelected();
            if(!noneSelectedBooleanValue()) controller.modifyBasedOnName(searchValue.getText(), runNum, getSelected());
        }
    }

    private class ModifyPlaceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            noneSelected();
            if(!noneSelectedBooleanValue()) controller.modifyBasedOnPlace(searchValue.getText(), runNum, getSelected());
        }
    }

    private class ModifyCommentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            noneSelected();
            if(!noneSelectedBooleanValue()) controller.modifyBasedOnComment(searchValue.getText(), runNum, getSelected());
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
