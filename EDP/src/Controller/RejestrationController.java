package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Service;
import View.RejestrationView;

public class RejestrationController implements ActionListener {

    RejestrationView view;
    public RejestrationController(RejestrationView view){
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        String id = ((JButton) event.getSource()).getText();
        if (id.equals("Wybierz rodzaj")) {
            Object[] selections = {"Naprawa", "Gwarancyjna", "Reklamacja"};
            Object val = JOptionPane.showInputDialog(
                    null, "Rodzaje napraw:", "Rodzaj naprawy",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, selections, selections[0]);
            if (val != null)
                view.statusText.setText(val.toString());
        } else if (id.equals("Wybierz model")) {
            int listSize = view.service.selectPhones().size();
            Object[] selections = new Object[listSize];
            for(int i = 0; i< view.service.selectPhones().size(); i++){
                selections[i] = view.service.selectPhones().get(i).getModel();
            }
            Object val = JOptionPane.showInputDialog(
                    null, "Dostępne modele", "Modele",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, selections, selections[0]);
            if (val != null)
                view.modelText.setText(val.toString());
        } else if (id.equals("Wybierz technika")) {
            int listSize = view.service.selectWorkers().size();
            Object[] selections = new Object[listSize];
            for(int i = 0; i< view.service.selectWorkers().size(); i++){
                selections[i] = view.service.selectWorkers().get(i).getAcronym();
            }
            Object val = JOptionPane.showInputDialog(
                    null, "Dostępni technicy", "Technicy",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, selections, selections[0]);
            if (val != null)
                view.workerText.setText(val.toString());
        }else if (id.equals("Rejestruj")) {
            try {
                String nrCase = view.caseText.getText();
                Long imei = Long.valueOf(view.imeiText.getText());
                String admissionDate = view.admissionText.getText();
                String status = view.statusText.getText();
                Integer model = view.service.idOfModel(view.modelText.getText());
                Integer worker = view.service.idOfAcronym(view.workerText.getText());
                String operator = view.operatorText.getText();
                synchronized (Service.getInstance()) {
                    view.service.insertRepair(model, worker, nrCase, imei, admissionDate, operator, status);
                }
                JOptionPane.showMessageDialog(null,
                        "Wprowadzono nowy przypadek", "Komunikat",
                        JOptionPane.INFORMATION_MESSAGE);

                view.caseText.setText(view.service.nextNrCase());
                view.imeiText.setText("");
                view.admissionText.setText(String.valueOf(java.time.LocalDate.now()));
                view.statusText.setText("Naprawa");
                view.modelText.setText("Galaxy S21");
                view.workerText.setText("PAMD");
                view.operatorText.setText("");
            }catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null,
                        "Nie wpisano wszystkich wartości ", "Puste pole",
                        JOptionPane.ERROR_MESSAGE);
            }catch (IllegalArgumentException illegal) {
                JOptionPane.showMessageDialog(null,
                        "Wpisano zły format daty ", "Błędny format",
                        JOptionPane.ERROR_MESSAGE);
            }catch (NullPointerException nullPointerException){
                JOptionPane.showMessageDialog(null,
                        "Nie wpisano wszystkich wartości ", "Nie wpisnięty przycisk",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

