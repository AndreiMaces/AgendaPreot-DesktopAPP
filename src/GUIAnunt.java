import javax.swing.*;

public class GUIAnunt {
    public GUIAnunt() {
    }

    public JPanel Vizualizare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vizualizare anunturi"));
        return panel;
    }

    public JPanel Adaugare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Adaugare anunt"));
        return panel;
    }
}
