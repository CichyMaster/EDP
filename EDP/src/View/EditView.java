package View;

import Controller.EditionController;
import Controller.MainPanel.EControl;
import Model.Service;
import Controller.Show;

import javax.swing.*;
import java.awt.*;

public class EditView extends JFrame {

    public Service service = Service.getInstance();
    Font frameFont = new Font("Arial", Font.BOLD, 16);
    Font buttonFont = new Font("Arial", Font.BOLD, 9);

    public JTextField editText, imeiText, statusText, modelText, workerText, operatorText, admissionText, endText;
    public JButton modelButton, workerButton, statusButton, dateButton, cancelButton, acceptButton;
    public EditView(EControl eControl){
        addWindowListener(eControl);
        EditionController editionController = new EditionController(this);

        //Tworzenie komponentów do wyboru edycji
        JLabel editLabel = new JLabel("Wpisz numer DCR");
        editText = new JTextField(15);
        editText.addActionListener(new Show(this, service));

        //Tworzenie komponentów, które będą podlegać zmianie
        JLabel imeiLabel = new JLabel("Imei:");
        imeiLabel.setFont(frameFont);
        imeiText = new JTextField(15);
        imeiText.setFont(frameFont);
        imeiText.setEditable(false);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(frameFont);
        modelText = new JTextField(15);
        modelText.setFont(frameFont);
        modelText.setEditable(false);
        modelButton = new JButton("Wybierz model");
        modelButton.setFont(buttonFont);
        modelButton.setEnabled(false);
        modelButton.addActionListener(editionController);

        JLabel workerLabel = new JLabel("Technik:");
        workerLabel.setFont(frameFont);
        workerText = new JTextField(15);
        workerText.setFont(frameFont);
        workerText.setEditable(false);
        workerButton = new JButton("Wybierz technika");
        workerButton.setFont(buttonFont);
        workerButton.setEnabled(false);
        workerButton.addActionListener(editionController);

        JLabel operatorLabel = new JLabel("Operator:");
        operatorLabel.setFont(frameFont);
        operatorText = new JTextField(15);
        operatorText.setFont(frameFont);
        operatorText.setEditable(false);

        JLabel statusLabel = new JLabel("Status naprawy:");
        statusLabel.setFont(frameFont);
        statusText = new JTextField(15);
        statusText.setFont(frameFont);
        statusText.setEditable(false);
        statusButton = new JButton("Wybierz rodzaj");
        statusButton.setFont(buttonFont);
        statusButton.setEnabled(false);
        statusButton.addActionListener(editionController);

        JLabel admissionLabel = new JLabel("Data przyjecia:");
        admissionLabel.setFont(frameFont);
        admissionText = new JTextField(15);
        admissionText.setFont(frameFont);
        admissionText.setEditable(false);

        JLabel endLabel = new JLabel("Data zakończenia:");
        endLabel.setFont(frameFont);
        endText = new JTextField(15);
        endText.setFont(frameFont);
        endText.setEditable(false);

        dateButton = new JButton("Ustaw datę");
        dateButton.setEnabled(false);
        dateButton.addActionListener(editionController);

        cancelButton = new JButton("Anuluj");
        cancelButton.setVisible(false);
        cancelButton.addActionListener(editionController);

        acceptButton = new JButton("Edytuj");
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(editionController);

        //Ustawiam ułożenie widoku
        JPanel topPane = new JPanel();
        topPane.add(editLabel);
        topPane.add(editText);
        topPane.add(cancelButton);

        JPanel bottomPane = new JPanel();
        bottomPane.add(imeiLabel);
        bottomPane.add(imeiText);
        bottomPane.add(modelLabel);
        bottomPane.add(modelText);
        bottomPane.add(modelButton);
        bottomPane.add(workerLabel);
        bottomPane.add(workerText);
        bottomPane.add(workerButton);
        bottomPane.add(operatorLabel);
        bottomPane.add(operatorText);
        bottomPane.add(statusLabel);
        bottomPane.add(statusText);
        bottomPane.add(statusButton);
        bottomPane.add(admissionLabel);
        bottomPane.add(admissionText);
        bottomPane.add(endLabel);
        bottomPane.add(endText);
        bottomPane.add(dateButton);
        bottomPane.add(acceptButton);

        bottomPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Konfiguracja etykiety imei
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        bottomPane.add(imeiLabel, gbc);

        // Konfiguracja pola tekstowego imei
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 50;
        bottomPane.add(imeiText, gbc);

        // Konfiguracja etykiety modelu
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        bottomPane.add(modelLabel, gbc);

        // Konfiguracja pola tekstowego modelu
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        bottomPane.add(modelText, gbc);

        // Konfiguracja przycisku modelów
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 5;
        bottomPane.add(modelButton, gbc);

        // Konfiguracja etykiety technika
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        bottomPane.add(workerLabel, gbc);

        // Konfiguracja pola tekstowego technika
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 50;
        bottomPane.add(workerText, gbc);

        // Konfiguracja przycisku technika
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        bottomPane.add(workerButton, gbc);

        // Konfiguracja etykiety operatora
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        bottomPane.add(operatorLabel, gbc);

        // Konfiguracja pola tekstowego operatora
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        bottomPane.add(operatorText, gbc);

        // Konfiguracja etykiety statusu naprawy
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        bottomPane.add(statusLabel, gbc);

        // Konfiguracja pola tekstowego statusu naprawy
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 50;
        bottomPane.add(statusText, gbc);

        // Konfiguracja przycisku statusu
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        bottomPane.add(statusButton, gbc);

        // Konfiguracja etykiety daty przyjęcia
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        bottomPane.add(admissionLabel, gbc);

        // Konfiguracja pola tekstowego daty przyjecia
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        bottomPane.add(admissionText, gbc);

        // Konfiguracja etykiety daty zakończenia
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        bottomPane.add(endLabel, gbc);

        // Konfiguracja pola tekstowego daty zakonczenia
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 50;
        bottomPane.add(endText, gbc);

        //Konfiguracja przycisku ustawiania daty
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        bottomPane.add(dateButton, gbc);

        //Konfiguracja przycisku akceptowania edycji
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.top = 20;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        gbc.insets.right = 20;
        bottomPane.add(acceptButton, gbc);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(topPane);
        splitPane.setBottomComponent(bottomPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);

        add(splitPane);
        pack();
        setLocationRelativeTo(null);
    }
}
