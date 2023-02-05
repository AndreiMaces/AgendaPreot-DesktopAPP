package Shared;

import javax.swing.*;
import java.awt.*;

public class ElementGUI {
    static public JPanel Titlu(String label)
    {
        JPanel TitlePanel = new JPanel();
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        JLabel title = new JLabel(label);
        title.setFont(new Font(Helper.font, Font.PLAIN, 36));
        TitlePanel.add(title, Helper.umpleLatimeButon());
        return TitlePanel;
    }
}
