package Shared;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static String font = "Arial";
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

    public static String traducereLuna(String luna) {
        if(luna.equals("JANUARY")) {
            return  "Ianuarie";
        }
        if(luna.equals("FEBRUARY")) {
            return  "Februarie";
        }

        if(luna.equals("MARCH")) {
            return  "Martie";
        }

        if(luna.equals("APRIL")) {
            return "Aprilie";
        }

        if(luna.equals("MAY")) {
            return  "Mai";
        }

        if(luna.equals("JUNE")) {
            return "Iunie";
        }

        if(luna.equals("JULY")) {
            return "Iulie";
        }

        if(luna.equals("AUGUST")) {
            return "August";
        }

        if(luna.equals("SEPTEMBER")) {
            return "Septembrie";
        }

        if(luna.equals("OCTOBER")) {
            return "Octombrie";
        }

        if(luna.equals("NOVEMBER")) {
            return "Noiembrie";
        }

        if(luna.equals("DECEMBER")) {
            return "Decembrie";
        }
        return "";
    }

}
