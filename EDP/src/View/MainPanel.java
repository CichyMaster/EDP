package View;

import Controller.MainPanel.DControl;
import Controller.MainPanel.EControl;
import Controller.MainPanel.RControl;
import Controller.MainPanel.SControl;

import javax.swing.*;

import java.awt.*;

public class MainPanel extends JFrame {
    MainPanel(){
        //Tworzenie komponentów Swing
        JButton rejestrationButton = new JButton("Rejestracja");
        JButton searchingButton = new JButton("Wyszukiwanie");
        JButton editButton = new JButton("Edytowanie");
        JButton deleteButton = new JButton("Usuwanie");
        JPanel ctrlPane = new JPanel();

        //Tworzenie obsługę zdarzeń dla konkretynch przycisków
        rejestrationButton.addActionListener(new RControl());
        searchingButton.addActionListener(new SControl());
        editButton.addActionListener(new EControl());
        deleteButton.addActionListener(new DControl());

        // Ustawienie czcionki i rozmiaru liter oraz wielkośći przycisków
        Font font = new Font("Arial", Font.BOLD, 30);
        Dimension dimension = new Dimension(250,100);

        //Ustawienie wielkości komponentów
        rejestrationButton.setPreferredSize(dimension);
        rejestrationButton.setFont(font);

        searchingButton.setPreferredSize(dimension);
        searchingButton.setFont(font);

        editButton.setPreferredSize(dimension);
        editButton.setFont(font);

        deleteButton.setPreferredSize(dimension);
        deleteButton.setFont(font);


        //Wyświetlanie komponetów w odpowiedniej konfiguracji
        add(ctrlPane);
        ctrlPane.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 100));
        ctrlPane.add(rejestrationButton);
        ctrlPane.add(searchingButton);
        ctrlPane.add(editButton);
        ctrlPane.add(deleteButton);

    }

    public static void main(String[] args) {
        SwingConsole.run(new MainPanel(), 800, 600, "Panel główny", 3);

    }
}












