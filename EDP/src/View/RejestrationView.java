package View;

import Controller.MainPanel.RControl;
import Controller.RejestrationController;
import Model.Service;

import javax.swing.*;
import java.awt.*;

public class RejestrationView extends JFrame {
    public Service service = Service.getInstance();
    Font frameFont = new Font("Arial", Font.BOLD, 16);
    Font buttonFont = new Font("Arial", Font.BOLD, 9);
    public JTextField caseText, imeiText, admissionText, statusText, modelText, workerText, operatorText;
    public RejestrationView(RControl rControl){
        addWindowListener(rControl);
        RejestrationController rejestrationController = new RejestrationController(this);

        JLabel caseLabel = new JLabel("Numer przypadku:");
        caseLabel.setFont(frameFont);
        caseText = new JTextField(service.nextNrCase(),15);
        caseText.setFont(frameFont);
        caseText.setForeground(Color.GRAY);
        caseText.setEditable(false);

        JLabel imeiLabel = new JLabel("Imei:");
        imeiLabel.setFont(frameFont);
        imeiText = new JTextField(15);
        imeiText.setFont(frameFont);

        JLabel admissionLabel = new JLabel("Data rejestracji:");
        admissionLabel.setFont(frameFont);
        admissionText = new JTextField(String.valueOf(java.time.LocalDate.now()), 15);
        admissionText.setFont(frameFont);

        JLabel statusLabel = new JLabel("Rodzaj naprawy:");
        statusLabel.setFont(frameFont);
        statusText = new JTextField("Naprawa",15);
        statusText.setFont(frameFont);
        JButton statusButton = new JButton("Wybierz rodzaj");
        statusButton.setFont(buttonFont);
        statusButton.addActionListener(rejestrationController);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(frameFont);
        modelText = new JTextField("Galaxy S21", 15);
        modelText.setFont(frameFont);
        JButton modelButton = new JButton("Wybierz model");
        modelButton.setFont(buttonFont);
        modelButton.addActionListener(rejestrationController);

        JLabel workerLabel = new JLabel("Technik:");
        workerLabel.setFont(frameFont);
        workerText = new JTextField("DACI", 15);
        workerText.setFont(frameFont);
        JButton workerButton = new JButton("Wybierz technika");
        workerButton.setFont(buttonFont);
        workerButton.addActionListener(rejestrationController);

        JLabel operatorLabel = new JLabel("Operator:");
        operatorLabel.setFont(frameFont);
        operatorText = new JTextField(15);
        operatorText.setFont(frameFont);

        JButton registerButton = new JButton("Rejestruj");
        registerButton.setFont(frameFont);
        registerButton.addActionListener(rejestrationController);

        JPanel ctrlPane = new JPanel();

        // Ustawienie menedżera układu na GridBagLayout
        ctrlPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Konfiguracja etykiety numer przypadku
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        ctrlPane.add(caseLabel, gbc);

        // Konfiguracja pola tekstowego numer przypadku
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 50;
        ctrlPane.add(caseText, gbc);

        // Konfiguracja etykiety imei
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        ctrlPane.add(imeiLabel, gbc);

        // Konfiguracja pola tekstowego imei
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        ctrlPane.add(imeiText, gbc);

        // Konfiguracja etykiety data przyjęcia
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        ctrlPane.add(admissionLabel, gbc);

        // Konfiguracja pola tekstowego data przyjecia
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 50;
        ctrlPane.add(admissionText, gbc);

        // Konfiguracja etykiety statusu
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        ctrlPane.add(statusLabel, gbc);

        // Konfiguracja pola tekstowego statusu
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        ctrlPane.add(statusText, gbc);

        // Konfiguracja przycisku wyboru statusu
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        ctrlPane.add(statusButton, gbc);

        // Konfiguracja etykiety modelu
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        ctrlPane.add(modelLabel, gbc);

        // Konfiguracja pola tekstowego modelu
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 50;
        ctrlPane.add(modelText, gbc);

        // Konfiguracja przycisku wyboru modelu
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        ctrlPane.add(modelButton, gbc);

        // Konfiguracja etykiety technika
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 0;
        ctrlPane.add(workerLabel, gbc);

        // Konfiguracja pola tekstowego technika
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 0;
        ctrlPane.add(workerText, gbc);

        // Konfiguracja przycisku wyboru technika
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 0;
        gbc.insets.left = 0;
        ctrlPane.add(workerButton, gbc);

        // Konfiguracja etykiety operatora
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets.bottom = 5;
        gbc.insets.left = 50;
        ctrlPane.add(operatorLabel, gbc);

        // Konfiguracja pola tekstowego operatora
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.bottom = 20;
        gbc.insets.left = 50;
        ctrlPane.add(operatorText, gbc);

        // Konfiguracja przycisku rejestracji
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.top = 10;
        gbc.insets.bottom = 46;
        gbc.insets.left = 10;
        gbc.insets.right = 50;
        ctrlPane.add(registerButton, gbc);

        add(ctrlPane);
        pack();
        setLocationRelativeTo(null);
    }

}

