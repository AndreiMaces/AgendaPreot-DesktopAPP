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
    private final Predica _context;
    public GUIPredica() throws IOException {

        _context = new Predica(_caleFisier);
    }

    public JScrollPane Vizualizare() throws IOException {
        return new JScrollPane(this.PanelVizualizare());
    }


    public JScrollPane Adaugare()
    {
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Adauga predica");
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton submitButton = new JButton("Adauga");
        panel.add(label);
        panel.add(scrollPane);
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textArea.getText().length() < 28) {
                        JOptionPane.showMessageDialog(null, "Predica trebuie sa aibe minim 28 de caractere!");
                        return;
                    }
                    _context.AdaugaPredica(textArea.getText());
                    RandeazaVizualizare(panel);
                    panel.revalidate();
                    panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return new JScrollPane(panel);
    }

    public JPanel PanelVizualizare() throws IOException {
        JPanel panel = new JPanel();
        RandeazaVizualizare(panel);
        return panel;
    }

    public void RandeazaVizualizare(JPanel panel) throws IOException {
        panel.removeAll();
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
        ArrayList<String> predici = _context.CitestePredici();
        for(int i = 0 ; i < predici.size() ; i++)
        {
            JPanel row = new JPanel();
            row.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JLabel stringLabel = new JLabel(predici.get(i).substring(0, 25) + "...");
            row.add(stringLabel, c);

            c.gridx = 1;
            c.weightx = 0.0;
            JButton editButton = new JButton("Edit");
            int finalI = i;
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RandeazaEditare(panel , finalI);
                    panel.revalidate();
                    panel.repaint();
                }
            });
            row.add(editButton, c);
            JButton deleteButton = new JButton("Delete");
            c.gridx = 2;
            row.add(deleteButton, c);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        _context.StergePredica(finalI);
                        RandeazaVizualizare(panel);
                        panel.revalidate();
                        panel.repaint();
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
    }

    private void RandeazaEditare(JPanel panel, int id)
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Editeaza predica");
        JTextArea textArea = new JTextArea(_context.CitestePredica(id));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton submitButton = new JButton("Salveaza");
        panel.add(label);
        panel.add(scrollPane);
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textArea.getText().length() < 28) {
                        JOptionPane.showMessageDialog(null, "Predica trebuie sa aibe minim 28 de caractere!");
                        return;
                    }
                    _context.EditeazaPredica(id, textArea.getText());
                    RandeazaVizualizare(panel);
                    panel.revalidate();
                    panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
