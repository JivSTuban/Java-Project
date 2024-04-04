import GUI.GamePanel;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Robo Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int windowWidth = 800; // window width
        int windowHeight = 600; // window height
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        window.setBounds(x, y, windowWidth, windowHeight);

        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
