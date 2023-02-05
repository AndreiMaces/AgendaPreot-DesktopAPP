import Data.ServiciuReligios;
import Enums.LabelServiciu;
import Shared.ElementGUI;
import Shared.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class GUIServiciuReligios {
    private final JPanel _panel;
    public GUIServiciuReligios() {
        _panel = new JPanel();
    }
    private class AscultatorButonStergereServiciu implements ActionListener {
        private final  Model.ServiciuReligios _ServiciiReligioase;
        private final JTable _table;
        public AscultatorButonStergereServiciu( Model.ServiciuReligios ServiciiReligioase, JTable table) {
            _ServiciiReligioase = ServiciiReligioase;
            this._table = table;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int row = _table.getSelectedRow();
                int rezultat = JOptionPane.showConfirmDialog(_panel,LabelServiciu.ConfirmareStergere.getLabel(), LabelServiciu.TitluMesajStergere.getLabel(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(rezultat == JOptionPane.YES_OPTION) {
                    _ServiciiReligioase.stergeServiciu(row);
                    RandareVizualizare();
                    _panel.repaint();
                    _panel.revalidate();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class AscultatorButonEditareServiciu implements ActionListener {
        private final JTable _table;
        public AscultatorButonEditareServiciu(JTable table) {
            this._table = table;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
                int row = _table.getSelectedRow();
                ArrayList<String> cells = new ArrayList<String>();
                for(int i = 0; i< _table.getColumnCount(); i++) {
                    cells.add(_table.getValueAt(row, i).toString());
                }
                RandeazaEditare(cells, row);
                _panel.repaint();
                _panel.revalidate();
        }
    }

    private class AscultatorButonPrintare implements  ActionListener {
        private JTable table;
        private DefaultTableModel tableModel;
        private ArrayList<ServiciuReligios> servicii;

        AscultatorButonPrintare(JTable table,DefaultTableModel tableModel, ArrayList<ServiciuReligios> servicii) {
            this.table = table;
            this.tableModel = tableModel;
            this.servicii = servicii;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String zi = Integer.toString(java.time.LocalDate.now().getDayOfMonth());
            String an = Integer.toString(java.time.LocalDate.now().getYear());
            String luna = java.time.LocalDate.now().getMonth().toString();
            luna = Helper.traducereLuna(luna).substring(0, 3);

            while(tableModel.getRowCount() > 0) tableModel.removeRow(0);
            for (ServiciuReligios serviciuReligios : servicii) {
                String[] informatii = serviciuReligios.getData().split(" ");
                if(zi.equals(informatii[0]) && luna.equals(informatii[1]) && an.equals(informatii[2])) {
                    Object[] linie = {serviciuReligios.getNume(), serviciuReligios.getData(), serviciuReligios.getOra(), serviciuReligios.getAdresa(), serviciuReligios.getNrTelefon()};
                    tableModel.addRow(linie);
                }
            }
            try {
                table.print();
                while(tableModel.getRowCount() > 0) tableModel.removeRow(0);
                for (ServiciuReligios serviciuReligios : servicii) {
                    Object[] linie = {serviciuReligios.getNume(), serviciuReligios.getData(), serviciuReligios.getOra(), serviciuReligios.getAdresa(), serviciuReligios.getNrTelefon()};
                    tableModel.addRow(linie);
                }
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        }

    public JPanel Vizualizare() throws IOException {
        RandareVizualizare();
        return _panel;
    }
    public JPanel Adaugare()
    {
        RandareAdaugare();
        return _panel;
    }
    private void RandareAdaugare() {
        Helper.ReconfigureazaPanou(_panel);
        _panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        interfataAdaugareSieditare(LabelServiciu.MetodaAdaugare.getLabel(),new ArrayList<>(),0);
    }
    private void RandareVizualizare() throws IOException {

        Helper.ReconfigureazaPanou(_panel);
        var PanelTitlu = ElementGUI.Titlu(LabelServiciu.TitluVizualizareServiciu.getLabel());
        JButton ButonPrintare = new JButton("Printeaza servicii de astazi");
        PanelTitlu.add(ButonPrintare);
        _panel.add(PanelTitlu);
        _panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));

        //GUI Buton de adaugare serviciu
        JPanel PanelButon = new JPanel();
        PanelButon.setLayout(new GridBagLayout());
        JButton ButonAdaugare = new JButton(LabelServiciu.TitluAdaugareServiciu.getLabel());

        PanelButon.add(ButonAdaugare, Helper.umpleLatimeButon());
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));

        Model.ServiciuReligios ServiciiReligioase = new Model.ServiciuReligios();
        ArrayList<ServiciuReligios> servicii = ServiciiReligioase.afisare();
        String[] col = {"Nume", "Data", "Ora", "Adresa", "Telefon"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (ServiciuReligios serviciuReligios : servicii) {
            Object[] data = {serviciuReligios.getNume(), serviciuReligios.getData(), serviciuReligios.getOra(), serviciuReligios.getAdresa(), serviciuReligios.getNrTelefon()};
            tableModel.addRow(data);
        }
        JTable table = new JTable() {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(row, row);
                }
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editeaza  = new JMenuItem(LabelServiciu.OptiuneEditare.getLabel());
        JMenuItem sterge = new JMenuItem(LabelServiciu.OptiuneStergere.getLabel());
        popupMenu.add(editeaza);
        popupMenu.add(sterge);

        table.setComponentPopupMenu(popupMenu);

        editeaza.addActionListener(new AscultatorButonEditareServiciu(table));

        sterge.addActionListener(new AscultatorButonStergereServiciu(ServiciiReligioase,table));
        ButonAdaugare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandareAdaugare();
                _panel.revalidate();
                _panel.repaint();
            }
        });

        ButonPrintare.addActionListener(new AscultatorButonPrintare(table,tableModel,servicii));
        _panel.add(PanelButon);
        _panel.add(new JScrollPane(table),BorderLayout.CENTER);
    }
    private void RandeazaEditare(ArrayList<String> cells, int id) {
        Helper.ReconfigureazaPanou(_panel);
        _panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        interfataAdaugareSieditare(LabelServiciu.MetodaEditare.getLabel(), cells,id);
    }
    private void adaugareSiEditareServiciu(String metoda,String nume, String data, String ora, String adresa, String telefon,int id) {
            try {

                if(nume.equals("") || !nume.matches("[a-zA-Z \\-]{0,40}")) {
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajValidareNume.getLabel());
                    return;
                }

                if(ora.equals("") || !ora.matches("^([0-1]?[0-9]|2[0-3])(:([0-5][0-9]))?$")) {
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajValidareOra.getLabel());
                    return;
                }

                if(adresa.equals("") || !adresa.matches("^^[0-9a-zA-Z .]{0,80}$")) {
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajValidareAdresa.getLabel());
                    return;
                }

                if(telefon.equals("") || !telefon.matches("^((07)\\d{8})|((\\+40)\\d{9})$")) {
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajValidareTelefon.getLabel());
                    return;
                }
                Data.ServiciuReligios serviciu = new Data.ServiciuReligios(nume,data,ora,adresa,telefon);
                Model.ServiciuReligios serviciiTotale = new Model.ServiciuReligios();
                if(metoda.equals(LabelServiciu.MetodaEditare.getLabel())) {
                    serviciiTotale.editeazaServiciu(id,serviciu);
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajSuccesEditare.getLabel());
                }
                else  {
                    serviciiTotale.adaugaServiciu(serviciu);
                    JOptionPane.showMessageDialog(_panel,LabelServiciu.MesajSuccesAdaugare.getLabel());
                }
                RandareVizualizare();
                _panel.revalidate();
                _panel.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    private void interfataAdaugareSieditare(String metoda,ArrayList<String> cells, int id) {
        //Start datepicker
        int year = Calendar.getInstance().get(Calendar.YEAR);
        _panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

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
        JButton Buton;
        if(metoda.equals(LabelServiciu.MetodaEditare.getLabel())){
            _panel.add(ElementGUI.Titlu(LabelServiciu.titluEditareServiciu.getLabel()));
            tNume.setText(cells.get(0));
            tOra.setText(cells.get(2));
            tAdresa.setText(cells.get(3));
            tTelefon.setText(cells.get(4));
            var date = cells.get(1).split(" ");
            yearComboBox.setSelectedItem(Integer.parseInt(date[2]));
            monthComboBox.setSelectedItem(date[1]);
            dayComboBox.setSelectedItem(Integer.parseInt(date[0]));
            Buton = new JButton(LabelServiciu.titluEditareServiciu.getLabel());
        } else {
            _panel.add(ElementGUI.Titlu(LabelServiciu.TitluAdaugareServiciu.getLabel()));
            Buton  = new JButton(LabelServiciu.TitluAdaugareServiciu.getLabel());
        }

        //GUI Formular
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
        PanelButon.add(Buton, Helper.umpleLatimeButon());
        PanelButon.setMaximumSize(new Dimension(PanelButon.getMaximumSize().width, 80));

        _panel.setLayout(new BoxLayout(_panel,BoxLayout.Y_AXIS));
        _panel.add(gridPanel);
        _panel.add(PanelButon);

        Buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume,data,ora,adresa,telefon;
                nume = tNume.getText().trim();
                data = Helper.formateazaData( dayComboBox.getItemAt(dayComboBox.getSelectedIndex()).toString(), monthComboBox.getItemAt(monthComboBox.getSelectedIndex()), yearComboBox.getItemAt(yearComboBox.getSelectedIndex()).toString()).trim();
                ora = tOra.getText().trim();
                adresa = tAdresa.getText().trim();
                telefon = tTelefon.getText().trim();
                if(metoda.equals(LabelServiciu.MetodaEditare.getLabel())) {
                    adaugareSiEditareServiciu(LabelServiciu.MetodaEditare.getLabel(),nume,data,ora,adresa,telefon,id);
                }
                else adaugareSiEditareServiciu(LabelServiciu.MetodaAdaugare.getLabel(), nume,data,ora,adresa,telefon,-1);
            }
        });

    }

}