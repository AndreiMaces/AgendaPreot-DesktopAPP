package Shared;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static String formateazaData(String zi, String luna, String an) {
        StringBuilder sb = new StringBuilder(zi);
        sb.append(" ");
        sb.append(luna);
        sb.append(" ");
        sb.append(an);
        return sb.toString();
    }

    public static void ReconfigureazaPanou(JPanel panel)
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public static GridBagConstraints umpleLatimeButon() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }
}
