package Controller;

import Model.Service;
import View.EditView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;

public class EditionController implements ActionListener {
    EditView view;

    public EditionController(EditView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String id = ((JButton) event.getSource()).getText();

        switch (id) {
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
            case "Ustaw datę":
                view.endText.setText(String.valueOf(java.time.LocalDate.now()));
                break;
            case "Anuluj":
                view.imeiText.setEditable(false);
                view.imeiText.setText(null);
                view.modelText.setEditable(false);
                view.modelText.setText(null);
                view.modelButton.setEnabled(false);
                view.workerText.setEditable(false);
                view.workerText.setText(null);
                view.workerButton.setEnabled(false);
                view.operatorText.setEditable(false);
                view.operatorText.setText(null);
                view.statusText.setEditable(false);
                view.statusText.setText(null);
                view.statusButton.setEnabled(false);
                view.admissionText.setEditable(false);
                view.admissionText.setText(null);
                view.endText.setEditable(false);
                view.endText.setText(null);
                view.acceptButton.setEnabled(false);
                view.editText.setEditable(true);
                view.dateButton.setEnabled(false);
                view.cancelButton.setVisible(false);
                break;
            case "Edytuj":
                try {
                    String nrCase = view.editText.getText();
                    long imei = Long.parseLong(view.imeiText.getText());
                    Integer model = view.service.idOfModel(view.modelText.getText());
                    Integer acronym = view.service.idOfAcronym(view.workerText.getText());
                    String operator = view.operatorText.getText();
                    String status = view.statusText.getText();
                    String admissionDate = view.admissionText.getText();
                    String endDate = view.endText.getText();

                    Object[] options = {"OK", "Anuluj"};
                    int sel = JOptionPane.showOptionDialog(
                            null, "Dokonanych zmian nie można cofnąć. Czy jesteś pewny, że chcesz wykonać tą operację?", "Ostrzeżenie",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null,
                            options, options[0]);
                    if (options[sel] == "OK") {
                        synchronized (Service.getInstance()) {
                            view.service.updateRepair(nrCase, imei, model, acronym, operator, status, admissionDate, endDate);
                        }
                        JOptionPane.showMessageDialog(null,
                                "Dokonano edycji przypadku " + nrCase.toUpperCase().trim(), "Potwierdzenie",
                                JOptionPane.INFORMATION_MESSAGE);

                        view.imeiText.setEditable(false);
                        view.imeiText.setText(null);
                        view.modelText.setEditable(false);
                        view.modelText.setText(null);
                        view.modelButton.setEnabled(false);
                        view.workerText.setEditable(false);
                        view.workerText.setText(null);
                        view.workerButton.setEnabled(false);
                        view.operatorText.setEditable(false);
                        view.operatorText.setText(null);
                        view.statusText.setEditable(false);
                        view.statusText.setText(null);
                        view.statusButton.setEnabled(false);
                        view.admissionText.setEditable(false);
                        view.admissionText.setText(null);
                        view.endText.setEditable(false);
                        view.endText.setText(null);
                        view.acceptButton.setEnabled(false);
                        view.editText.setEditable(true);
                        view.dateButton.setEnabled(false);
                        view.cancelButton.setVisible(false);
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null,
                            "Nie wpisano wszystkich wartości ", "Błędny format",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException illegal) {
                    JOptionPane.showMessageDialog(null,
                            "Wpisano zły format daty ", "Błędny format",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(null,
                            "Nie wpisano wszystkich wartości ", "Brak technika/telefonu",
                            JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeException dateTimeException) {
                    JOptionPane.showMessageDialog(null,
                            "Data przyjęcia jest później niż data zakończenia\nlub brak daty przyjęcia ", "Błędne daty",
                            JOptionPane.ERROR_MESSAGE);
                }
            default:
                break;
        }
    }
}
