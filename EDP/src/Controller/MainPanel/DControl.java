package Controller.MainPanel;

import View.DeleteView;
import View.SwingConsole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DControl extends WindowAdapter implements ActionListener {

    private boolean openWindow = false;
    @Override
    public void actionPerformed(ActionEvent event) {
        if(!openWindow) {
            SwingConsole.run(new DeleteView(this), 800, 300, "Usuwanie", 2);
            openWindow = true;
        }
    }

    public void windowClosed(WindowEvent e) {
        openWindow = false;
    }
}
