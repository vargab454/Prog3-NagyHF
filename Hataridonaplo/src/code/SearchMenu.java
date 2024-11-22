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
        searchName.addActionListener(new SearchNameListener());
        searchName.setPreferredSize(new Dimension(400, 60));
        JButton searchPlace = new JButton("Keresés helyszín alapján");
        searchPlace.addActionListener(new SearchPlaceListener());
        searchPlace.setPreferredSize(new Dimension(400, 60));
        JButton searchComment = new JButton("Keresés megjegyzés alapján");
        searchComment.addActionListener(new SearchCommentListener());
        searchComment.setPreferredSize(new Dimension(400, 60));
        JButton back = new JButton("Vissza");
        back.setPreferredSize(new Dimension(400, 60));
        back.addActionListener(new ExitListener());
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

    public void start() { setVisible(true); }

    public void close() { setVisible(false); }
    
    private class SearchNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            String searched = searchValue.getText();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownNameCompare(searched)), "Azon események, melyek neve tartalmazza a '" + searched + "'részletet", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SearchPlaceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            String searched = searchValue.getText();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownPlaceCompare(searched)), "Azon események, melyek helyszíne tartalmazza a '" + searched + "' tagot", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SearchCommentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            String searched = searchValue.getText();
            JOptionPane.showMessageDialog(null, controller.toScrollableTable(controller.toShownCommentCompare(searched)), "Azon események, melyek megjegyzése tartalmazza a '" + searched + "' tagot", JOptionPane.INFORMATION_MESSAGE);
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
