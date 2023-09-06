package View;

import javax.swing.*;
import java.awt.*;

public class SwingConsole{
    public static void run(final JFrame f, final int width, final int height, String title, int close){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle(title);
                f.setDefaultCloseOperation(close);
                f.setSize(width, height);
                f.setVisible(true);

                //Określenie rozmiaru ekranu
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenWidth = screenSize.width;
                int screenHeight = screenSize.height;

                // Określenie pozycji dla JFrame
                int frameWidth = f.getSize().width;
                int frameHeight = f.getSize().height;
                int frameX = (screenWidth - frameWidth) / 2;
                int frameY = (screenHeight - frameHeight) / 2;

                // Ustawianie pozycji JFrame na środku ekranu
                f.setLocation(frameX, frameY);

            }
        });

    }
}