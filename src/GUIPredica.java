import Model.Predica;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUIPredica {
    private String _caleFisier = "C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\Predici.txt";
    public GUIPredica() {
    }

    public JScrollPane Vizualizare() throws IOException {
        return new JScrollPane(this.PanelVizualizare());
    }


    public JPanel Adaugare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Adaugare predica"));
        return panel;
    }

    public JPanel PanelVizualizare() throws IOException {
        var Model = new Predica(_caleFisier);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("Predici");
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        panel.add(TitlePanel);
        ArrayList<String> predici = Model.CitestePredici();
        for(int i = 0 ; i < predici.size() ; i++)
        {
            JPanel row = new JPanel();
            row.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JLabel stringLabel = new JLabel(predici.get(i));
            row.add(stringLabel, c);

            c.gridx = 1;
            c.weightx = 0.0;
            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            row.add(editButton, c);
            JButton deleteButton = new JButton("Delete");
            c.gridx = 2;
            row.add(deleteButton, c);
            int finalI = i;
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Model.StergePredica(finalI);
                        panel.repaint();
                        panel.revalidate();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
            Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            row.setBorder(BorderFactory.createCompoundBorder(border, margin));

            panel.add(row);
        }
        return panel;
    }
}
