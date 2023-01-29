import javax.swing.*;

public class GUIPredica {
    public GUIPredica() {
    }

    public JPanel Vizualizare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vizualizare predici"));
        return panel;
    }

    public JPanel Adaugare()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Adaugare predica"));
        return panel;
    }
}
