package Controller;

import Model.Service;
import Model.TableContents;
import View.EditView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Show implements ActionListener {
    EditView view;
    Service service;

    public Show(EditView editView, Service service) {
        this.view = editView;
        this.service = service;
    }

    private String changeOfNull(String text){
        if(!text.equals("null")){
            return text;
        }else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String numberDCR = view.editText.getText();

        if (numberDCR != null && !"".equals(numberDCR)) {
            for (Object[] o : TableContents.contents()) {
                if (String.valueOf(o[0]).equals(numberDCR.toUpperCase().trim())) {
                    view.imeiText.setEditable(true);
                    view.imeiText.setText(String.valueOf(o[1]));
                    view.modelText.setEditable(true);
                    view.modelText.setText(String.valueOf(o[2]));
                    view.modelButton.setEnabled(true);
                    view.workerText.setEditable(true);
                    view.workerText.setText(String.valueOf(o[3]));
                    view.workerButton.setEnabled(true);
                    view.operatorText.setEditable(true);
                    view.operatorText.setText(changeOfNull(String.valueOf(o[4])));
                    view.statusText.setEditable(true);
                    view.statusText.setText(changeOfNull(String.valueOf(o[5])));
                    view.statusButton.setEnabled(true);
                    view.admissionText.setEditable(true);
                    view.admissionText.setText(changeOfNull(String.valueOf(o[6])));
                    view.endText.setEditable(true);
                    view.endText.setText(changeOfNull(String.valueOf(o[7])));
                    view.dateButton.setEnabled(true);
                    view.acceptButton.setEnabled(true);
                    view.editText.setEditable(false);
                    view.cancelButton.setVisible(true);
                }
            }
            if (view.imeiText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nie znaleziono przypadku", "Error message",
                    JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,
                    "Proszę wprowadzić tekst", "Error message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
