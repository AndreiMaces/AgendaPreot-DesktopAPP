import Enums.LabelAnunt;
import Enums.PanouAnunt;
import Model.Anunt;
import Shared.ElementGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUIAnunt {
    private final JPanel _panel;
    private final Anunt _context;
    private JTextArea _textArea;
    class ElementGUIAnunt {
        public ButonAnunt Buton;
        public ElementGUIAnunt()
        {
            Buton = new ButonAnunt();
        }
        private JPanel Anunt(String text, int i)
        {
            JPanel row = new JPanel();
            row.setMaximumSize(new Dimension(row.getMaximumSize().width, 50));
            row.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JLabel stringLabel = new JLabel(text.length() > 28 ? text.substring(0, 25) + "..." : text);
            row.add(stringLabel, c);

            c.gridx = 1;
            c.weightx = 0.0;
            JButton ButonRandareEditareAnunt = new ButonAnunt().ButonRandareEditareAnunt(i);
            row.add(ButonRandareEditareAnunt, c);

            JButton ButonStergereAnunt = new ButonAnunt().ButonStergereAnunt(i);
            c.gridx = 2;
            row.add(ButonStergereAnunt, c);

            JButton ButonVizualizareAnunt = new ButonAnunt().ButonVizualizareAnunt(i);
            c.gridx = 3;
            row.add(ButonVizualizareAnunt, c);

            Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
            Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            row.setBorder(BorderFactory.createCompoundBorder(border, margin));
            return row;
        }
        public JScrollPane PanouAnunt(PanouAnunt tip, int index)
        {
            switch (tip)
            {
                case Vizualizare -> {
                    JTextArea textArea = new JTextArea(_context.CitesteAnunt(index));
                    _textArea = textArea;
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    return new JScrollPane(textArea);
                }
                case Editare -> {
                    JTextArea textArea = new JTextArea(_context.CitesteAnunt(index));
                    _textArea = textArea;
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(true);
                    return new JScrollPane(textArea);
                }
                case Adaugare -> {
                    JTextArea textArea = new JTextArea();
                    _textArea = textArea;
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(true);
                    return new JScrollPane(textArea);
                }
            }
            return null;
        }
        public JPanel PanouListaAnunturi()
        {
            JPanel PanouListaAnunturi = new JPanel();
            PanouListaAnunturi.setLayout(new BoxLayout(PanouListaAnunturi, BoxLayout.Y_AXIS));

            ArrayList<String> Anunturi = _context.CitesteAnunturi();
            for(int i = 0 ; i < Anunturi.size() ; i++)
            {
                PanouListaAnunturi.add(new ElementGUIAnunt().Anunt(Anunturi.get(i) , i));
            }
            return PanouListaAnunturi;
        }
    }
    class ButonAnunt {
        class AscultatorButonAdaugareAnunt implements ActionListener {

            private final JTextArea _textArea;
            public AscultatorButonAdaugareAnunt(JTextArea textArea)
            {
                _textArea = textArea;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(_textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, LabelAnunt.MesajEroareLimitaCaractere.getLabel());
                        return;
                    }
                    if(_textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, LabelAnunt.MesajEroareDelimitator.getLabel());
                        return;
                    }
                    _context.AdaugaAnunt(_textArea.getText());
                    RandeazaVizualizareListaAnunturi();
                    _panel.revalidate();
                    _panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

        class AscultatorButonRandareAdaugareAnunt implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                RandareAdaugare();
                _panel.revalidate();
                _panel.repaint();
            }
        }

        class AscultatorButonVizualizareAnunt implements ActionListener {
            private final int _index;

            public AscultatorButonVizualizareAnunt(int index)
            {
                _index = index;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                RandeazaVizualizareAnunt(_index);
                _panel.revalidate();
                _panel.repaint();
            }
        }

        class AscultatorButonRandareEditareAnunt implements ActionListener {
            private final int _index;
            public AscultatorButonRandareEditareAnunt(int index)
            {
                _index = index;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                RandeazaEditare(_index);
                _panel.revalidate();
                _panel.repaint();
            }

        }

        class AscultatorButonStergereAnunt implements ActionListener {
            private final int _index;
            public AscultatorButonStergereAnunt(int index)
            {
                _index = index;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Window parent = SwingUtilities.getWindowAncestor(_panel);
                    int rezultat = JOptionPane.showConfirmDialog(parent,"Sunteti sigur ca vreti sa stergeti anuntul?", "Sterge Anunt",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(rezultat == JOptionPane.YES_OPTION) {
                        _context.StergeAnunt(_index);
                        RandeazaVizualizareListaAnunturi();
                        _panel.revalidate();
                        _panel.repaint();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        class AscultatorButonEditareAnunt implements ActionListener {

            private final int _index;
            private final JTextArea _textArea;

            public AscultatorButonEditareAnunt(JTextArea textArea, int index)
            {
                _textArea = textArea;
                _index = index;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(_textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, "Anunt trebuie sa aibe minim 10 de caractere!");
                        return;
                    }
                    if(_textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, "Anunt nu poate contine caracterul @!");
                        return;
                    }
                    _context.EditeazaAnunt(_index, _textArea.getText());
                    RandeazaVizualizareListaAnunturi();
                    _panel.revalidate();
                    _panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        class AscultatorButonVizualizareListaAnunturi implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RandeazaVizualizareListaAnunturi();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                _panel.revalidate();
                _panel.repaint();
            }

        }
        public JPanel ButonAdaugareAnunt() {

            JPanel PanelButon = new JPanel();
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonAdaugareAnunt = new JButton(LabelAnunt.ButonAdaugareAnunt.getLabel());
            ButonAdaugareAnunt.addActionListener(new AscultatorButonAdaugareAnunt(_textArea));
            PanelButon.add(ButonAdaugareAnunt, c);

            return PanelButon;
        }
        public JPanel ButonRandareAdaugareAnunt() {

            JPanel PanelButon = new JPanel();
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;

            JButton ButonRandareAdaugareAnunt = new JButton(LabelAnunt.ButonAdaugareAnunt.getLabel());
            ButonRandareAdaugareAnunt.addActionListener(new AscultatorButonRandareAdaugareAnunt());
            PanelButon.add(ButonRandareAdaugareAnunt, c);
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));

            return PanelButon;
        }
        public JButton ButonVizualizareAnunt( int index) {
            JButton ButonVizualizareAnunt = new JButton(LabelAnunt.ButonVizualizareAnunt.getLabel());
            ButonVizualizareAnunt.addActionListener(new AscultatorButonVizualizareAnunt(index));
            return ButonVizualizareAnunt;
        }
        public JButton ButonRandareEditareAnunt(int index) {
            JButton ButonRandareEditareAnunt = new JButton(LabelAnunt.ButonEditareAnunt.getLabel());
            ButonRandareEditareAnunt.addActionListener(new AscultatorButonRandareEditareAnunt(index));
            return ButonRandareEditareAnunt;
        }
        public JButton ButonStergereAnunt(int index) {
            JButton ButonStergereAnunt = new JButton(LabelAnunt.ButonStergereAnunt.getLabel());
            ButonStergereAnunt.addActionListener(new AscultatorButonStergereAnunt(index));
            return ButonStergereAnunt;
        }
        public JPanel ButonEditareAnunt(int index) {
            JPanel PanelButon = new JPanel();
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonEditareAnunt = new JButton(LabelAnunt.ButonEditareAnunt.getLabel());
            ButonEditareAnunt.addActionListener(new AscultatorButonEditareAnunt(_textArea, index));
            PanelButon.add(ButonEditareAnunt, c);

            return PanelButon;
        }
        public JPanel ButonVizualizareListaAnunturi() {
            JPanel PanouButon = new JPanel();
            PanouButon.setMaximumSize(new Dimension(PanouButon.getMaximumSize().width, 80));
            PanouButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonVizualizareListaAnunturi = new JButton(LabelAnunt.ButonVizualizareListaAnunturi.getLabel());
            ButonVizualizareListaAnunturi.addActionListener(new AscultatorButonVizualizareListaAnunturi());
            PanouButon.add(ButonVizualizareListaAnunturi, c);
            return PanouButon;
        }
    }
    public GUIAnunt() throws IOException
    {

        String _caleFisier = "src/Model/Anunturi.txt";
        _context = new Anunt(_caleFisier);
        _panel = new JPanel();
    }
    public JScrollPane Vizualizare() throws IOException
    {
        RandeazaVizualizareListaAnunturi();
        return new JScrollPane(_panel);
    }
    public JScrollPane Adaugare()
    {
        RandareAdaugare();
        return new JScrollPane(_panel);
    }
    private void RandareAdaugare()
    {
        ReconfigureazaPanou();
        _panel.add(ElementGUI.Titlu(LabelAnunt.TitluAdaugareAnunt.getLabel()));
        _panel.add(new ElementGUIAnunt().PanouAnunt(PanouAnunt.Adaugare, -1));
        _panel.add(new ElementGUIAnunt().Buton.ButonAdaugareAnunt());
    }
    public void RandeazaVizualizareListaAnunturi() throws IOException
    {
        ReconfigureazaPanou();

        _panel.add(ElementGUI.Titlu(LabelAnunt.TitluVizualizareListaAnunturi.getLabel()));
        _panel.add(new ButonAnunt().ButonRandareAdaugareAnunt());
        _panel.add(new ElementGUIAnunt().PanouListaAnunturi());
    }
    private void RandeazaEditare(int index)
    {
        ReconfigureazaPanou();
        _panel.add(ElementGUI.Titlu(LabelAnunt.TitluEditareAnunt.getLabel()));
        _panel.add(new ElementGUIAnunt().PanouAnunt(PanouAnunt.Editare, index));
        _panel.add(new ElementGUIAnunt().Buton.ButonEditareAnunt(index));
    }
    private void RandeazaVizualizareAnunt(int index)
    {
        ReconfigureazaPanou();
        _panel.add(ElementGUI.Titlu(LabelAnunt.TitluVizualizareAnunt.getLabel()));
        _panel.add(new ElementGUIAnunt().PanouAnunt(PanouAnunt.Vizualizare, index));
        _panel.add(new ElementGUIAnunt().Buton.ButonVizualizareListaAnunturi());
    }
    private void ReconfigureazaPanou()
    {
        _panel.removeAll();
        _panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
        _panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
