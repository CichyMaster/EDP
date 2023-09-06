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

        switch (id){
            case "Wybierz rodzaj":
                Object[] statusSelections = {"Naprawa", "Gwarancyjna", "Reklamacja"};
                Object statusDialog = JOptionPane.showInputDialog(
                        null, "Rodzaje napraw:", "Rodzaj naprawy",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, statusSelections, statusSelections[0]);
                if (statusDialog != null)
                    view.statusText.setText(statusDialog.toString());
                break;
            case "Wybierz model":
                int modelListSize = view.service.selectPhones().size();
                Object[] modelSelections = new Object[modelListSize];
                for (int i = 0; i < view.service.selectPhones().size(); i++) {
                    modelSelections[i] = view.service.selectPhones().get(i).getModel();
                }
                Object modelDialog = JOptionPane.showInputDialog(
                        null, "Dostępne modele", "Modele",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, modelSelections, modelSelections[0]);
                if (modelDialog != null)
                    view.modelText.setText(modelDialog.toString());
                break;
            case "Wybierz technika":
                int workerListSize = view.service.selectWorkers().size();
                Object[] workerSelections = new Object[workerListSize];
                for (int i = 0; i < view.service.selectWorkers().size(); i++) {
                    workerSelections[i] = view.service.selectWorkers().get(i).getAcronym();
                }
                Object workerDialog = JOptionPane.showInputDialog(
                        null, "Dostępni technicy", "Technicy",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, workerSelections, workerSelections[0]);
                if (workerDialog != null)
                    view.workerText.setText(workerDialog.toString());
                break;
            case "Rejestruj":
                try {
                    String nrCase = view.caseText.getText();
                    long imei = Long.parseLong(view.imeiText.getText());
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
                            "Nie wpisano wszystkich wartości ", "Brak technika/telefonu",
                            JOptionPane.ERROR_MESSAGE);
                }
            default:
                break;
        }
    }
}

