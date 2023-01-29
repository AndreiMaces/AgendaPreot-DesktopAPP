import Data.ServiciuReligios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class GUIServiciuReligios {
    public GUIServiciuReligios() {
    }

    public JPanel Vizualizare()  {
        JPanel panel = new JPanel();
        String[] col = {"Name", "Date", "Ora", "Adresa", "Telefon"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        panel.setLayout(new GridLayout());
        try {
            Model.ServiciuReligios ServiciiReligioase = new Model.ServiciuReligios();
            ArrayList<ServiciuReligios> servicii = ServiciiReligioase.afisare();
            for (ServiciuReligios serviciu : servicii) {
                Object[] data = {serviciu.getNume(),serviciu.getData(),serviciu.getOra(),serviciu.getAdresa(),serviciu.getNrTelefon()};
                tableModel.addRow(data);
            }
            JTable table = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(table);
            panel.add(sp,BorderLayout.CENTER);
            return panel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }

    public JPanel Adaugare()
    {
        JPanel panel = new JPanel();
        try{
            JTextField tNume = new JTextField(15);
            JTextField tData = new JTextField(15);
            JTextField tOra = new JTextField(15);
            JTextField tAdresa = new JTextField(15);
            JTextField tTelefon = new JTextField(15);

            JLabel lNume = new JLabel("Nume:");
            JLabel lData = new JLabel("Data:");
            JLabel lOra = new JLabel("Ora:");
            JLabel lAdresa = new JLabel("Adresa:");
            JLabel lTelefon = new JLabel("Telefon:");
            panel.setLayout(new GridLayout(5,2));
            tNume.setSize(50,55);
            panel.add(lNume);panel.add(tNume);
            panel.add(lData);panel.add(tData);
            panel.add(lOra);panel.add(tOra);
            panel.add(lAdresa);panel.add(tAdresa);
            panel.add(lTelefon);panel.add(tTelefon);

            //panel.add(new JLabel("Adaugare serviciu religios"));
            Model.ServiciuReligios ServiciiReligioase = new Model.ServiciuReligios();
            //Data.ServiciuReligios serv = new Data.ServiciuReligios("Panait Andrei","20.2.3", "12", "camin fn", "ciolan");
            //ServiciiReligioase.adaugaServiciu(serv,"C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\ServiciiReligioase");
            return panel;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       return panel;
    }
}
