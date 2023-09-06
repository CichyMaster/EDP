package Controller;

import Model.Service;
import View.EditView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditionController implements ActionListener {
    EditView view;

    public EditionController(EditView view) {
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
            for (int i = 0; i < view.service.selectPhones().size(); i++) {
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
            for (int i = 0; i < view.service.selectWorkers().size(); i++) {
                selections[i] = view.service.selectWorkers().get(i).getAcronym();
            }
            Object val = JOptionPane.showInputDialog(
                    null, "Dostępni technicy", "Technicy",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, selections, selections[0]);
            if (val != null)
                view.workerText.setText(val.toString());
        } else if (id.equals("Ustaw datę")) {

            view.endText.setText(String.valueOf(java.time.LocalDate.now()));

        } else if (id.equals("Anuluj")) {
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
        } else if (id.equals("Edytuj")) {
            try {
                String nrCase = view.editText.getText();
                Long imei = Long.valueOf(view.imeiText.getText());
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
                            "Dokonano edycji przypadku " + nrCase, "Potwierdzenie",
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
            }catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null,
                        "Nie wpisano wszystkich wartości ", "Błędny format",
                        JOptionPane.ERROR_MESSAGE);
            }catch (IllegalArgumentException illegal) {
                JOptionPane.showMessageDialog(null,
                        "Wpisano zły format daty ", "Błędny format",
                        JOptionPane.ERROR_MESSAGE);
            }catch (NullPointerException nullPointerException){
                JOptionPane.showMessageDialog(null,
                        "Nie wpisano wszystkich wartości ", "Błędny format",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
