package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGameSettings {
    public static GamePanel getGamePanel() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mitsu Realm");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int windowWidth = 1920; // window width
        int windowHeight = 1080; // window height
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        window.setBounds(x, y, windowWidth, windowHeight);

        window.setVisible(true);
        gamePanel.setupGame();
        return gamePanel;
    }
}
