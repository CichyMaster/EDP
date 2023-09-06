package Controller;

import Model.Service;
import Model.TableContents;
import View.DeleteView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteController implements ActionListener {
    private final Service service = Service.getInstance();
    private final DeleteView view;
    private final DefaultTableModel model;
    public DeleteController(DeleteView view, DefaultTableModel model){
        this.view = view;
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String searchText = view.textField.getText();

        if(event.getSource() == view.textField) {
            view.tableScrollPane.setVisible(true);
            view.deleteButton.setVisible(true);
            view.deleteButton.setEnabled(true);
            if (searchText != null && !"".equals(searchText)) {
                Object[][] newData = new Object[1][1];
                for (Object[] o : TableContents.contents()) {
                    if (String.valueOf(o[0]).startsWith(searchText.toUpperCase().trim())) {
                        newData[0] = o;
                    }
                }
                model.setDataVector(newData, TableContents.TABLE_HEADER);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Proszę wprowadzić tekst", "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else if(event.getSource() == view.deleteButton){

            Object[] options = {"OK", "Anuluj"};
            int sel = JOptionPane.showOptionDialog(
                    null, "Czy napewno chcesz usunąć przypadek " + searchText + "?", "Ostrzeżenie",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null,
                    options, options[0]);
            if (options[sel] == "OK") {
                for (Object[] o : TableContents.contents()) {
                    if (String.valueOf(o[0]).equals(searchText.toUpperCase().trim())) {
                        synchronized (Service.getInstance()) {
                            service.deleteRepair(searchText);
                        }
                        JOptionPane.showMessageDialog(null,
                                "Usunięto przypadek " + searchText, "Informacja",
                                JOptionPane.INFORMATION_MESSAGE);
                        model.setDataVector(null, TableContents.TABLE_HEADER);
                        view.deleteButton.setEnabled(false);
                        break;
                    }
                }
            }
        }

    }
}
