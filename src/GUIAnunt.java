import Model.Anunt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUIAnunt
{
    private String _caleFisier = "src/Model/Anunturi.txt";
    private final Anunt _context;
    public GUIAnunt() throws IOException
    {

        _context = new Anunt(_caleFisier);
    }
    public JScrollPane Vizualizare() throws IOException
    {
        JPanel panel = new JPanel();
        RandeazaVizualizare(panel);
        return new JScrollPane(panel);
    }
    public JScrollPane Adaugare()
    {
        JPanel panel;
        panel = new JPanel();
        RandareAdaugare(panel);
        return new JScrollPane(panel);
    }
    private void RandareAdaugare(JPanel panel)
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("Adaugare Anunt");
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel PanelButon = new JPanel();
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
        PanelButon.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton submitButton = new JButton("Adauga");
        PanelButon.add(submitButton, c);
        panel.add(TitlePanel);
        panel.add(scrollPane);
        panel.add(PanelButon);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, "Anunt trebuie sa aibe minim 10 de caractere!");
                        return;
                    }
                    if(textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, "Anunt nu poate contine caracterul @!");
                        return;
                    }
                    _context.AdaugaAnunt(textArea.getText());
                    RandeazaVizualizare(panel);
                    panel.revalidate();
                    panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void RandeazaVizualizare(JPanel panel) throws IOException
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel TitlePanel = new JPanel();
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("Anunturi");
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        panel.add(TitlePanel);
        ArrayList<String> Anunturi = _context.CitesteAnunturi();
        for(int i = 0 ; i < Anunturi.size() ; i++)
        {
            JPanel row = new JPanel();
            row.setMaximumSize(new Dimension(row.getMaximumSize().width, 50));
            row.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JLabel stringLabel = new JLabel(Anunturi.get(i).length() > 28 ? Anunturi.get(i).substring(0, 25) + "..." : Anunturi.get(i));
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
                        _context.StergeAnunt(finalI);
                        RandeazaVizualizare(panel);
                        panel.revalidate();
                        panel.repaint();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton viewButton = new JButton("Vizualizeaza");
            c.gridx = 3;
            row.add(viewButton, c);
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RandeazaVizualizareId(panel, finalI);
                    panel.revalidate();
                    panel.repaint();
                }
            });

            Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
            Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            row.setBorder(BorderFactory.createCompoundBorder(border, margin));
            panel.add(row);
        }

        JPanel PanelButon = new JPanel();
        PanelButon.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton ButonAdaugare = new JButton("Adauga Anunt");
        PanelButon.add(ButonAdaugare, c);
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
        ButonAdaugare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandareAdaugare(panel);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(PanelButon);
    }
    private void RandeazaEditare(JPanel panel, int id)
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel TitlePanel = new JPanel();
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("Editeaza Anunt");
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        JTextArea textArea = new JTextArea(_context.CitesteAnunt(id));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel PanelButon = new JPanel();
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
        PanelButon.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton submitButton = new JButton("Salveaza Anunt");
        PanelButon.add(submitButton, c);
        panel.add(TitlePanel);
        panel.add(scrollPane);
        panel.add(PanelButon);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, "Anunt trebuie sa aibe minim 10 de caractere!");
                        return;
                    }
                    if(textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, "Anunt nu poate contine caracterul @!");
                        return;
                    }
                    _context.EditeazaAnunt(id, textArea.getText());
                    RandeazaVizualizare(panel);
                    panel.revalidate();
                    panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void RandeazaVizualizareId(JPanel panel, int id)
    {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextArea textArea = new JTextArea(_context.CitesteAnunt(id));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        JPanel PanelButon = new JPanel();
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
        PanelButon.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton submitButton = new JButton("Inapoi");
        PanelButon.add(submitButton, c);
        panel.add(scrollPane);
        panel.add(PanelButon);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RandeazaVizualizare(panel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                panel.revalidate();
                panel.repaint();
            }
        });
    }
}
