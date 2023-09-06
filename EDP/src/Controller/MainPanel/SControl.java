package Controller.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import View.SearchView;
import View.SwingConsole;
public class SControl extends WindowAdapter implements ActionListener {
    private boolean openWindow = false;
    @Override
    public void actionPerformed(ActionEvent event) {
        if(!openWindow) {
            SwingConsole.run(new SearchView(this), 800, 600, "Wyszukiwanie", 2);
            openWindow = true;
        }
    }

    public void windowClosed(WindowEvent e) {
        openWindow = false;
    }
}
