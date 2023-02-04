import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class MainGUI {
    private JComponent currentPanel;
    public MainGUI()
    {

        //Creating the Frame
        JFrame frame = new JFrame("Agenda Preot");
        Image icon = Toolkit.getDefaultToolkit().getImage("cross.png");
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        mb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

        JLabel MeniuCalendar = new JLabel("Sarbatoare actuala.");
        mb.add(MeniuCalendar);

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
                try {
                    currentPanel = new GUIPredica().Adaugare();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuAnunturi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                try {
                    currentPanel = new GUIAnunt().Vizualizare();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.getContentPane().add(BorderLayout.CENTER, currentPanel);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });

        MeniuAnunturi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(currentPanel);
                try {
                    currentPanel = new GUIAnunt().Adaugare();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

        MeniuCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Controller.Calendar calendar = new Controller.Calendar();
                String zi = Integer.toString(java.time.LocalDate.now().getDayOfMonth());
                String an = Integer.toString(java.time.LocalDate.now().getYear());
                String luna = java.time.LocalDate.now().getMonth().toString();
                JOptionPane.showMessageDialog(frame, calendar.zi(an,traducereLuna(luna),zi).get("text"));
            }
        });
    }

    private String traducereLuna(String luna) {
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
