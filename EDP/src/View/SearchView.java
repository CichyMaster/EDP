package View;

import Controller.MainPanel.SControl;
import Controller.SearchController;
import Model.TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SearchView extends JFrame {
    public SearchView(SControl sControl){
        addWindowListener(sControl);

        //Tworzenie komponentów interfejsu graficznego
        JTextField searchTextField = new JTextField(11);
        JButton searchButton = new JButton("Szukaj");
        JTable table = new JTable();

        //Tworzenie modelu tabeli
        TableModel model = new TableModel();
        table.setModel(model);

        //Podłączenie kontrolera
        searchButton.addActionListener(new SearchController(searchTextField, model));

        //Ustawiam ułożenie widoku
        JPanel ctrlPane = new JPanel();
        ctrlPane.add(searchTextField);
        ctrlPane.add(searchButton);


        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Spis wszystkich przypadków",
                TitledBorder.CENTER, TitledBorder.TOP));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);

        //Dodanie tabeli do frame'a
        add(splitPane);
        pack();
        setLocationRelativeTo(null);
    }
}
