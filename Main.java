import GUI.GamePanel;
import GUI.MainGameSettings;

import java.io.IOException;


class Main extends MainGameSettings {
    public static void main(String[] args) throws IOException {
   //     LoginForm loginForm = new LoginForm(null);
        GamePanel gamePanel = getGamePanel();
        gamePanel.startGameThread();
    }
}