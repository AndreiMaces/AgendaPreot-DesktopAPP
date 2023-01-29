import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainGUI {
    private JComponent currentPanel;
    public MainGUI()
    {

        //Creating the Frame
        JFrame frame = new JFrame("Agenda Preot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu MeniuServiciiReligioase = new JMenu("Servicii Religioase");
        mb.add(MeniuServiciiReligioase);
        JMenuItem MeniuServiciiReligioase1 = new JMenuItem("Vizualizeaza");
        JMenuItem MeniuServiciiReligioase2 = new JMenuItem("Adauga");
        MeniuServiciiReligioase.add(MeniuServiciiReligioase1);
        MeniuServiciiReligioase.add(MeniuServiciiReligioase2);

        JMenu MeniuPredici = new JMenu("Predici");
        mb.add(MeniuPredici);
        JMenuItem MeniuPredici1 = new JMenuItem("Vizualizeaza");
        JMenuItem MeniuPredici2 = new JMenuItem("Adauga");
        MeniuPredici.add(MeniuPredici1);
        MeniuPredici.add(MeniuPredici2);

        JMenu MeniuAnunturi = new JMenu("Anunturi");
        mb.add(MeniuAnunturi);
        JMenuItem MeniuAnunturi1 = new JMenuItem("Vizualizeaza");
        JMenuItem MeniuAnunturi2 = new JMenuItem("Adauga");
        MeniuAnunturi.add(MeniuAnunturi1);
        MeniuAnunturi.add(MeniuAnunturi2);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        currentPanel = new GUIServiciuReligios().Vizualizare();
        frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
        frame.setVisible(true);


        MeniuPredici1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                try {
                    currentPanel = new GUIPredica().Vizualizare();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();

            }
        });

        MeniuPredici2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                currentPanel = new GUIPredica().Adaugare();
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuAnunturi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                currentPanel = new GUIAnunt().Vizualizare();
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuAnunturi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                currentPanel = new GUIAnunt().Adaugare();
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuServiciiReligioase1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                currentPanel = new GUIServiciuReligios().Vizualizare();
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuServiciiReligioase2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                currentPanel = new GUIServiciuReligios().Adaugare();
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });
    }
}
