import Data.ServiciuReligios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class GUIServiciuReligios {
    public GUIServiciuReligios() {
    }

    public JPanel Vizualizare()  {
        JPanel panel = new JPanel();
        RandareVizualizare(panel);
        return panel;
    }

    public JPanel Adaugare()
    {
        JPanel returnPanel = new JPanel();
        RandareAdaugare(returnPanel);
        return returnPanel;
    }
    private void RandareAdaugare(JPanel returnPanel) {
        returnPanel.removeAll();
        JPanel gridPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JTextField tNume = new JTextField(15);
        JTextField tData = new JTextField(15);
        JTextField tOra = new JTextField(15);
        JTextField tAdresa = new JTextField(15);
        JTextField tTelefon = new JTextField(15);
        JButton adauga = new JButton("adauga");

        JLabel lNume = new JLabel("Nume:");
        JLabel lData = new JLabel("Data:");
        JLabel lOra = new JLabel("Ora:");
        JLabel lAdresa = new JLabel("Adresa:");
        JLabel lTelefon = new JLabel("Telefon:");
        gridPanel.setLayout(new GridLayout(5,2));
        gridPanel.add(lNume);gridPanel.add(tNume);
        gridPanel.add(lData);gridPanel.add(tData);
        gridPanel.add(lOra);gridPanel.add(tOra);
        gridPanel.add(lAdresa);gridPanel.add(tAdresa);
        gridPanel.add(lTelefon);gridPanel.add(tTelefon);

        buttonPanel.add(adauga);
        returnPanel.setLayout(new BoxLayout(returnPanel,BoxLayout.Y_AXIS));
        returnPanel.add(gridPanel);
        returnPanel.add(buttonPanel);

        adauga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nume,data,ora,adresa,telefon;
                    nume = tNume.getText();
                    data = tData.getText();
                    ora = tOra.getText();
                    adresa = tAdresa.getText();
                    telefon = tTelefon.getText();
                    if(nume.equals("") || !nume.matches("[a-zA-Z \\-]*")) {
                        JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti un nume valid.");
                        return;
                    }

                    if(data.equals("") || !data.matches("[a-zA-Z \\-.0-9]*")) {
                        JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti o data valida.");
                        return;
                    }

                    if(ora.equals("") || !ora.matches("[0-9a-zA-Z :]*")) {
                        JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti o ora valida.");
                        return;
                    }

                    if(adresa.equals("") || !adresa.matches("[0-9a-zA-Z .]*")) {
                        JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti o adresa valida.");
                        return;
                    }

                    if(telefon.equals("") || !telefon.matches("^\\07?([0-9]{10,12})$")) {
                        JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti un numar de telefon valid.");
                        return;
                    }
                    Data.ServiciuReligios serviciu = new Data.ServiciuReligios(nume,data,ora,adresa,telefon);
                    Model.ServiciuReligios serviciiTotale = new Model.ServiciuReligios();
                    serviciiTotale.adaugaServiciu(serviciu);
                    JOptionPane.showMessageDialog(returnPanel,"Adaugare efectuata cu succes.");
                    RandareVizualizare(returnPanel);
                    returnPanel.revalidate();
                    returnPanel.repaint();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void RandareVizualizare(JPanel panel)
    {
        panel.removeAll();
        String[] col = {"Name", "Date", "Ora", "Adresa", "Telefon"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        panel.setLayout(new GridLayout());
        try {
            Model.ServiciuReligios ServiciiReligioase = new Model.ServiciuReligios();
            ArrayList<ServiciuReligios> servicii = ServiciiReligioase.afisare();
            for (int i = servicii.size() - 1 ; i >= 0; i--) {
                Object[] data = {servicii.get(i).getNume(),servicii.get(i).getData(),servicii.get(i).getOra(),servicii.get(i).getAdresa(),servicii.get(i).getNrTelefon()};
                tableModel.addRow(data);
            }
            JTable table = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(table);
            panel.add(sp,BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

