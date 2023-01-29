import javax.swing.*;

public class GUIServiciuReligios {
    public GUIServiciuReligios() {
    }

    public JPanel Vizualizare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vizualizare servicii religioase"));
        return panel;
    }

    public JPanel Adaugare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Adaugare serviciu religios"));
        return panel;
    }
}
