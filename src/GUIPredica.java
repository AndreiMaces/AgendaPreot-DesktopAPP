import Enums.LabelPredica;
import Enums.PanouPredica;
import Model.Predica;
import Shared.ElementGUI;
import Shared.Helper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUIPredica {
    private final JPanel _panel;
    private final Predica _context;
    private JTextArea _textArea;
    class ElementGUIPredica {
        public ButonPredica Buton;
        public ElementGUIPredica()
        {
            Buton = new ButonPredica();
        }
        private JPanel Predica(String text, int i)
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
            JButton ButonRandareEditarePredica = new ButonPredica().ButonRandareEditarePredica(i);
            row.add(ButonRandareEditarePredica, c);

            JButton ButonStergerePredica = new ButonPredica().ButonStergerePredica(i);
            c.gridx = 2;
            row.add(ButonStergerePredica, c);

            JButton ButonVizualizarePredica = new ButonPredica().ButonVizualizarePredica(i);
            c.gridx = 3;
            row.add(ButonVizualizarePredica, c);

            Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
            Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            row.setBorder(BorderFactory.createCompoundBorder(border, margin));
            return row;
        }
        public JScrollPane PanouPredica(PanouPredica tip, int index)
        {
            switch (tip)
            {
                case Vizualizare -> {
                    JTextArea textArea = new JTextArea(_context.CitestePredica(index));
                    _textArea = textArea;
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    return new JScrollPane(textArea);
                }
                case Editare -> {
                    JTextArea textArea = new JTextArea(_context.CitestePredica(index));
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
        public JPanel PanouListaPredici()
        {
            JPanel PanouListaPredici = new JPanel();
            PanouListaPredici.setLayout(new BoxLayout(PanouListaPredici, BoxLayout.Y_AXIS));

            ArrayList<String> predici = _context.CitestePredici();
            for(int i = 0 ; i < predici.size() ; i++)
            {
                PanouListaPredici.add(new ElementGUIPredica().Predica(predici.get(i) , i));
            }
            return PanouListaPredici;
        }
    }
    class ButonPredica {
        class AscultatorButonAdaugarePredica implements ActionListener {

            private final JTextArea _textArea;
            public AscultatorButonAdaugarePredica(JTextArea textArea)
            {
                _textArea = textArea;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(_textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, LabelPredica.MesajEroareLimitaCaractere.getLabel());
                        return;
                    }
                    if(_textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, LabelPredica.MesajEroareDelimitator.getLabel());
                        return;
                    }
                    _context.AdaugaPredica(_textArea.getText());
                    RandeazaVizualizareListaPredici();
                    _panel.revalidate();
                    _panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

        class AscultatorButonRandareAdaugarePredica implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                RandareAdaugare();
                _panel.revalidate();
                _panel.repaint();
            }
        }

        class AscultatorButonVizualizarePredica implements ActionListener {
            private final int _index;

            public AscultatorButonVizualizarePredica(int index)
            {
                _index = index;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                RandeazaVizualizarePredica(_index);
                _panel.revalidate();
                _panel.repaint();
            }
        }

        class AscultatorButonRandareEditarePredica implements ActionListener {
            private final int _index;
            public AscultatorButonRandareEditarePredica(int index)
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

        class AscultatorButonStergerePredica implements ActionListener {
            private final int _index;
            public AscultatorButonStergerePredica(int index)
            {
                _index = index;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Window parent = SwingUtilities.getWindowAncestor(_panel);
                    int rezultat = JOptionPane.showConfirmDialog(parent,"Sunteti sigur ca vreti sa stergeti predica?", "Sterge Predica",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(rezultat == JOptionPane.YES_OPTION) {
                        _context.StergePredica(_index);
                        RandeazaVizualizareListaPredici();
                        _panel.revalidate();
                        _panel.repaint();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        class AscultatorButonEditarePredica implements ActionListener {

            private final int _index;
            private final JTextArea _textArea;

            public AscultatorButonEditarePredica(JTextArea textArea, int index)
            {
                _textArea = textArea;
                _index = index;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(_textArea.getText().length() < 10) {
                        JOptionPane.showMessageDialog(null, "Predica trebuie sa aibe minim 10 de caractere!");
                        return;
                    }
                    if(_textArea.getText().contains("@"))
                    {
                        JOptionPane.showMessageDialog(null, "Predica nu poate contine caracterul @!");
                        return;
                    }
                    _context.EditeazaPredica(_index, _textArea.getText());
                    RandeazaVizualizareListaPredici();
                    _panel.revalidate();
                    _panel.repaint();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        class AscultatorButonVizualizareListaPredici implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RandeazaVizualizareListaPredici();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                _panel.revalidate();
                _panel.repaint();
            }

        }
        public JPanel ButonAdaugarePredica() {

            JPanel PanelButon = new JPanel();
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonAdaugarePredica = new JButton(LabelPredica.ButonAdaugarePredica.getLabel());
            ButonAdaugarePredica.addActionListener(new AscultatorButonAdaugarePredica(_textArea));
            PanelButon.add(ButonAdaugarePredica, c);

            return PanelButon;
        }
        public JPanel ButonRandareAdaugarePredica() {

            JPanel PanelButon = new JPanel();
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;

            JButton ButonRandareAdaugarePredica = new JButton(LabelPredica.ButonAdaugarePredica.getLabel());
            ButonRandareAdaugarePredica.addActionListener(new AscultatorButonRandareAdaugarePredica());
            PanelButon.add(ButonRandareAdaugarePredica, c);
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));

            return PanelButon;
        }
        public JButton ButonVizualizarePredica( int index) {
            JButton ButonVizualizarePredica = new JButton(LabelPredica.ButonVizualizarePredica.getLabel());
            ButonVizualizarePredica.addActionListener(new AscultatorButonVizualizarePredica(index));
            return ButonVizualizarePredica;
        }
        public JButton ButonRandareEditarePredica(int index) {
            JButton ButonRandareEditarePredica = new JButton(LabelPredica.ButonEditarePredica.getLabel());
            ButonRandareEditarePredica.addActionListener(new AscultatorButonRandareEditarePredica(index));
            return ButonRandareEditarePredica;
        }
        public JButton ButonStergerePredica(int index) {
            JButton ButonStergerePredica = new JButton(LabelPredica.ButonStergerePredica.getLabel());
            ButonStergerePredica.addActionListener(new AscultatorButonStergerePredica(index));
            return ButonStergerePredica;
        }
        public JPanel ButonEditarePredica(int index) {
            JPanel PanelButon = new JPanel();
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonEditarePredica = new JButton(LabelPredica.ButonEditarePredica.getLabel());
            ButonEditarePredica.addActionListener(new AscultatorButonEditarePredica(_textArea, index));
            PanelButon.add(ButonEditarePredica, c);

            return PanelButon;
        }
        public JPanel ButonVizualizareListaPredici() {
            JPanel PanouButon = new JPanel();
            PanouButon.setMaximumSize(new Dimension(PanouButon.getMaximumSize().width, 80));
            PanouButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton ButonVizualizareListaPredici = new JButton(LabelPredica.ButonVizualizareListaPredici.getLabel());
            ButonVizualizareListaPredici.addActionListener(new AscultatorButonVizualizareListaPredici());
            PanouButon.add(ButonVizualizareListaPredici, c);
            return PanouButon;
        }
    }

    public GUIPredica() throws IOException
    {

        String _caleFisier = "src/Model/Predici.txt";
        _context = new Predica(_caleFisier);
        _panel = new JPanel();
    }
    public JScrollPane Vizualizare() throws IOException
    {
        RandeazaVizualizareListaPredici();
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
        _panel.add(ElementGUI.Titlu(LabelPredica.TitluAdaugarePredica.getLabel()));
        _panel.add(new ElementGUIPredica().PanouPredica(PanouPredica.Adaugare, -1));
        _panel.add(new ElementGUIPredica().Buton.ButonAdaugarePredica());
    }
    public void RandeazaVizualizareListaPredici() throws IOException
    {
        ReconfigureazaPanou();

        _panel.add(ElementGUI.Titlu(LabelPredica.TitluVizualizareListaPredici.getLabel()));
        _panel.add(new ButonPredica().ButonRandareAdaugarePredica());
        _panel.add(new ElementGUIPredica().PanouListaPredici());
    }
    private void RandeazaEditare(int index)
    {
        ReconfigureazaPanou();
        _panel.add(ElementGUI.Titlu(LabelPredica.TitluEditarePredica.getLabel()));
        _panel.add(new ElementGUIPredica().PanouPredica(PanouPredica.Editare, index));
        _panel.add(new ElementGUIPredica().Buton.ButonEditarePredica(index));
    }
    private void RandeazaVizualizarePredica(int index)
    {
        ReconfigureazaPanou();
        _panel.add(ElementGUI.Titlu(LabelPredica.TitluVizualizarePredica.getLabel()));
        _panel.add(new ElementGUIPredica().PanouPredica(PanouPredica.Vizualizare, index));
        _panel.add(new ElementGUIPredica().Buton.ButonVizualizareListaPredici());
    }
    private void ReconfigureazaPanou()
    {
        Helper.ReconfigureazaPanou(_panel);
    }
}
