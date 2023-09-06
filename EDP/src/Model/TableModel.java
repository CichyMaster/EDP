package Model;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

    public TableModel(){
        super(TableContents.contents(), TableContents.TABLE_HEADER);
    }
    public TableModel(Object[][] content){
        super(content, TableContents.TABLE_HEADER);
    }

}
