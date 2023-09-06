package View;

import Controller.DeleteController;
import Controller.MainPanel.DControl;
import Model.TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DeleteView extends JFrame {
    public JPanel centeredPanel;
    public JTextField textField;
    public JButton deleteButton;
    public JScrollPane tableScrollPane;
    public DeleteView(DControl dControl){
        addWindowListener(dControl);

        //Dodawanie komponentów
        JLabel hintLabel = new JLabel("Wprowadź DCR");
        textField = new JTextField(15);
        deleteButton = new JButton("Usuń");
        JTable table = new JTable();

        //Tworzenie modelu tabeli
        TableModel model = new TableModel(null);
        table.setModel(model);

        //Podłączenie kontrolera
        textField.addActionListener(new DeleteController(this, model));
        deleteButton.addActionListener(new DeleteController(this, model));

        //Ustawiam ułożenie widoku
        centeredPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(700, 60));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Wykaz telefonu do usunięcia",
                TitledBorder.CENTER, TitledBorder.TOP));

        //Ukrycie początkowych komponentów
        tableScrollPane.setVisible(false);
        deleteButton.setVisible(false);

        // Konfiguracja etykiety
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        centeredPanel.add(hintLabel, gbc);

        // Konfiguracja pola tekstowego
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        centeredPanel.add(textField, gbc);

        //Konfiguracja tabeli
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        centeredPanel.add(tableScrollPane, gbc);

        //Konfiguracja przycisku usuwania
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        centeredPanel.add(deleteButton, gbc);

        //Dodanie komponentów do frame'a
        add(centeredPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
