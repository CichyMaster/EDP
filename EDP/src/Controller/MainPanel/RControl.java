package Controller.MainPanel;

import View.RejestrationView;
import View.SwingConsole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RControl extends WindowAdapter implements ActionListener {
    private boolean openWindow = false;
    @Override
    public void actionPerformed(ActionEvent event) {
        if(!openWindow) {
            SwingConsole.run(new RejestrationView(this), 800, 450, "Rejestracja", 2);
            openWindow = true;
        }
    }

    public void windowClosed(WindowEvent e) {
        openWindow = false;
    }
}
