package GUI;

import LoginRegister.LoginForm;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainGameSettings {

    public static GamePanel getGamePanel(LoginForm loginForm) throws SQLException, IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mitsu Realm");
        GamePanel gamePanel = new GamePanel(loginForm.user);
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
        gamePanel.setupGame(loginForm);
        return gamePanel;
    }
}
