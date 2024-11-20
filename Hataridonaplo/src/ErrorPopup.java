import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class ErrorPopup extends JFrame {
    
    JTextArea errorMessage;

    public ErrorPopup(String error){
        super("Határidőnapló - Error");
        errorMessage = new JTextArea(error);
        errorMessage.setEditable(false);
        errorMessage.setBorder(null);
        errorMessage.setBackground(null);
        errorMessage.setLineWrap(true);
        errorMessage.setWrapStyleWord(true);
        errorMessage.setPreferredSize(new Dimension(280, 80));
        JPanel panel = new JPanel();
        panel.add(errorMessage, BorderLayout.WEST);
        setBounds(800, 550,300, 80);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        add(panel);
    }
}
