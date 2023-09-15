package Controller;

import Model.TableContents;
import javax.swing.table.DefaultTableModel;

public class SearchingThread implements Runnable{
    private final String searchText;
    private final DefaultTableModel model;
    public SearchingThread(String searchText, DefaultTableModel model){
        super();
        this.searchText = searchText;
        this.model = model;
    }
    @Override
    public void run() {
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
    }
}
