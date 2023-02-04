import Data.ServiciuReligios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        returnPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        interfataAdaugareSieditare(returnPanel,"adaugare",new ArrayList<>(),0);
    }
    private void RandareVizualizare(JPanel panel)
    {
        //Initializare panou
        panel.removeAll();
        String[] col = {"Nume", "Data", "Ora", "Adresa", "Telefon"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        //GUI titlu
        JPanel TitlePanel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        TitlePanel.setMaximumSize(new Dimension(TitlePanel.getMaximumSize().width, 100));
        TitlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel title = new JLabel("Servicii Religioase");
        title.setFont(new Font("Serif", Font.PLAIN, 36));
        TitlePanel.add(title, c);
        panel.add(TitlePanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //GUI Buton de adaugare serviciu
        JPanel PanelButon = new JPanel();
        PanelButon.setLayout(new GridBagLayout());
        JButton ButonAdaugare = new JButton("Adauga serviciu religios");
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

        //GUI pentru tabel
        JPopupMenu popupMenu =new JPopupMenu();
        try {
            Model.ServiciuReligios ServiciiReligioase = new Model.ServiciuReligios();
            ArrayList<ServiciuReligios> servicii = ServiciiReligioase.afisare();
            for (ServiciuReligios serviciuReligios : servicii) {
                Object[] data = {serviciuReligios.getNume(), serviciuReligios.getData(), serviciuReligios.getOra(), serviciuReligios.getAdresa(), serviciuReligios.getNrTelefon()};
                tableModel.addRow(data);
            }
            JTable table = new JTable(tableModel);

            JMenuItem editeaza  = new JMenuItem("Editeaza");
            JMenuItem sterge = new JMenuItem("Sterge");
            popupMenu.add(editeaza);
            popupMenu.add(sterge);


            table.setComponentPopupMenu(popupMenu);
            JScrollPane sp = new JScrollPane(table);
            sp.setLayout(new ScrollPaneLayout());
            panel.add(sp,BorderLayout.CENTER);

            editeaza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if(row != -1)
                    {ArrayList<String> cells = new ArrayList<String>();
                        for(int i = 0; i< table.getColumnCount(); i++) {
                            cells.add(table.getValueAt(row, i).toString());
                        }
                        RandeazaEditare(panel,cells, row);
                        panel.repaint();
                        panel.revalidate();}
                    else {
                        JOptionPane.showMessageDialog(panel,"Va rog selectati un serviciu.");
                    }
                }

            });

            sterge.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int row = table.getSelectedRow();
                        if(row != -1){
                            int rezultat = JOptionPane.showConfirmDialog(panel,"Sunteti sigur ca vreti sa stergeti serviciul?", "Sterge Serviciu",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                            if(rezultat == JOptionPane.YES_OPTION) {
                                ServiciiReligioase.stergeServiciu(row);
                                RandareVizualizare(panel);
                                panel.repaint();
                                panel.revalidate();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(panel,"Va rog selectati un serviciu.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RandeazaEditare(JPanel returnPanel,ArrayList<String> cells, int id) {
        returnPanel.removeAll();
        returnPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        interfataAdaugareSieditare(returnPanel,"editare",cells,id);
    }

    private String formateazaData(String zi, String luna, String an) {
        StringBuilder sb = new StringBuilder(zi);
        sb.append(" ");
        sb.append(luna);
        sb.append(" ");
        sb.append(an);
        return sb.toString();
    }

        private void adaugareSiEditareServiciu(JPanel returnPanel,String metoda,String nume, String data, String ora, String adresa, String telefon,int id) {
            try {

                if(nume.equals("") || !nume.matches("[a-zA-Z \\-]{0,40}")) {
                    JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti un nume valid.\nNumele nu poate fi gol sau sa contina mai mult de 40 de caractere.");
                    return;
                }

                if(ora.equals("") || !ora.matches("^([0-1]?[0-9]|2[0-3])(:([0-5][0-9]))?$")) {
                    JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti o ora valida.");
                    return;
                }

                if(adresa.equals("") || !adresa.matches("^^[0-9a-zA-Z .]{0,80}$")) {
                    JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti o adresa valida.\nAdresa nu poate fi goala sau sa contina mai mult de 80 de caractere");
                    return;
                }

                if(telefon.equals("") || !telefon.matches("^((07)\\d{8})|((\\+40)\\d{9})$")) {
                    JOptionPane.showMessageDialog(returnPanel,"Va rog introduceti un numar de telefon valid.");
                    return;
                }
                Data.ServiciuReligios serviciu = new Data.ServiciuReligios(nume,data,ora,adresa,telefon);
                Model.ServiciuReligios serviciiTotale = new Model.ServiciuReligios();
                if(metoda.equals("editare")) {
                    serviciiTotale.editeazaServiciu(id,serviciu);
                    JOptionPane.showMessageDialog(returnPanel,"Editare efectuata cu succes.");
                }
                else if(metoda.equals("adaugare")) {
                    serviciiTotale.adaugaServiciu(serviciu);
                    JOptionPane.showMessageDialog(returnPanel,"Adaugare efectuata cu succes.");
                }
                RandareVizualizare(returnPanel);
                returnPanel.revalidate();
                returnPanel.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private void interfataAdaugareSieditare(JPanel returnPanel,String metoda,ArrayList<String> cells, int id) {
            //Start datepicker
            int year = Calendar.getInstance().get(Calendar.YEAR);
            returnPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

            JPanel datePickerPanel = new JPanel();
            datePickerPanel.setLayout(new FlowLayout());


            JLabel yearLabel = new JLabel("An:");

            Integer[] years = new Integer[100];
            for (int i = 0; i < 100; i++) {
                years[i] = year - 25 + i;
            }
            JComboBox<Integer> yearComboBox = new JComboBox<>(years);

            JLabel monthLabel = new JLabel("Luna:");

            String[] months = new String[]{"Ian", "Feb", "Mar", "Apr", "Mai", "Iun", "Iul", "Aug", "Sep", "Oct", "Noi", "Dec"};
            JComboBox<String> monthComboBox = new JComboBox<>(months);

            JLabel dayLabel = new JLabel("Zi:");

            Integer[] days = new Integer[31];
            for (int i = 0; i < 31; i++) {
                days[i] = i + 1;
            }
            JComboBox<Integer> dayComboBox = new JComboBox<>(days);
            datePickerPanel.add(dayLabel);
            datePickerPanel.add(dayComboBox);
            datePickerPanel.add(monthLabel);
            datePickerPanel.add(monthComboBox);
            datePickerPanel.add(yearLabel);
            datePickerPanel.add(yearComboBox);
            //Final datepicker

            JPanel gridPanel = new JPanel();
            JTextField tNume = new JTextField(15);
            JTextField tOra = new JTextField(15);
            JTextField tAdresa = new JTextField(15);
            JTextField tTelefon = new JTextField(15);
            if(metoda.equals("editare")){
                tNume.setText(cells.get(0));
                tOra.setText(cells.get(2));
                tAdresa.setText(cells.get(3));
                tTelefon.setText(cells.get(4));
                var date = cells.get(1).split(" ");
                yearComboBox.setSelectedItem(Integer.parseInt(date[2]));
                monthComboBox.setSelectedItem(date[1]);
                dayComboBox.setSelectedItem(Integer.parseInt(date[0]));
            }


            JLabel lNume = new JLabel("Nume:");
            JLabel lData = new JLabel("Data:");
            JLabel lOra = new JLabel("Ora:");
            JLabel lAdresa = new JLabel("Adresa:");
            JLabel lTelefon = new JLabel("Telefon:");
            gridPanel.setLayout(new GridLayout(5,2));
            gridPanel.add(lNume);gridPanel.add(tNume);
            gridPanel.add(lData);gridPanel.add(datePickerPanel);
            gridPanel.add(lOra);gridPanel.add(tOra);
            gridPanel.add(lAdresa);gridPanel.add(tAdresa);
            gridPanel.add(lTelefon);gridPanel.add(tTelefon);
            gridPanel.setMaximumSize(new Dimension(1000, 150));

            JPanel PanelButon = new JPanel();
            PanelButon.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            JButton Buton;
            if(metoda.equals("editare")) {
                Buton = new JButton("Editeaza serviciul religios");
            }
            else Buton  = new JButton("Adauga serviciu religios");
            PanelButon.add(Buton, c);
            PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));

            returnPanel.setLayout(new BoxLayout(returnPanel,BoxLayout.Y_AXIS));
            returnPanel.add(gridPanel);
            returnPanel.add(PanelButon);

            Buton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nume,data,ora,adresa,telefon;
                    nume = tNume.getText();
                    data = formateazaData( dayComboBox.getItemAt(dayComboBox.getSelectedIndex()).toString(), monthComboBox.getItemAt(monthComboBox.getSelectedIndex()), yearComboBox.getItemAt(yearComboBox.getSelectedIndex()).toString());
                    ora = tOra.getText();
                    adresa = tAdresa.getText();
                    telefon = tTelefon.getText();
                    if(metoda.equals("editare")) {
                        adaugareSiEditareServiciu(returnPanel,"editare",nume,data,ora,adresa,telefon,id);
                    }
                    else adaugareSiEditareServiciu(returnPanel,"adaugare",nume,data,ora,adresa,telefon,0);
                }
            });

        }
}



