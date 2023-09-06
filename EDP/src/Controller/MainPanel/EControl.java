package Controller.MainPanel;

import View.EditView;
import View.RejestrationView;
import View.SearchView;
import View.SwingConsole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EControl extends WindowAdapter implements ActionListener {
    private boolean openWindow = false;
    @Override
    public void actionPerformed(ActionEvent event) {
        if(!openWindow) {
            SwingConsole.run(new EditView(this), 800, 500, "Edycja", 2);
            openWindow = true;
        }
    }

    public void windowClosed(WindowEvent e) {
        openWindow = false;
    }
}
