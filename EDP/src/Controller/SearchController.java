package Controller;

import Model.TableContents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener {
    private JTextField searchTextField;
    private DefaultTableModel model;

    public SearchController(JTextField searchTextField, DefaultTableModel model){
        super();
        this.searchTextField = searchTextField;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String searchText = searchTextField.getText();
        if(searchText != null && !"".equals(searchText)){
            Object[][] newData = new Object[TableContents.contents().length][];
            int index = 0;
            for(Object[] o : TableContents.contents()){
                if(String.valueOf(o[0]).startsWith(searchText.toUpperCase().trim()) || String.valueOf(o[1]).startsWith(searchText.toUpperCase().trim())){
                    newData[index++] = o;
                }else if(" ".equals(searchText)){
                    newData[index++] = o;
                }
            }
            model.setDataVector(newData, TableContents.TABLE_HEADER);
        }else{
            JOptionPane.showMessageDialog(null,
                    "Proszę wprowadzić tekst", "Error message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
