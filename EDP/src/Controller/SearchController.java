package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener {
    private final JTextField searchTextField;
    private final DefaultTableModel model;

    public SearchController(JTextField searchTextField, DefaultTableModel model){
        super();
        this.searchTextField = searchTextField;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String searchText = searchTextField.getText();
        if(searchText != null && !"".equals(searchText)){
            Thread thread = new Thread(new SearchingThread(searchText, model));
            thread.start();
        }else{
            JOptionPane.showMessageDialog(null,
                    "Proszę wprowadzić tekst", "Error message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
