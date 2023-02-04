package Shared;

import javax.swing.*;
import java.awt.*;

public class ElementGUI {
    static public JPanel Titlu(String label)
    {
        JPanel TitlePanel = new JPanel();
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel(label);
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        return TitlePanel;
    }
}
