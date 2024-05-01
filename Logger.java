import GUI.GamePanel;
import LoginRegister.LoginForm;
import LoginRegister.RegistrationForm;

import java.io.IOException;
import java.sql.SQLException;

import static GUI.MainGameSettings.getGamePanel;

public class Logger {
    LoginForm loginForm = null;
    RegistrationForm registrationForm = null;
    public Logger() throws SQLException, IOException{


        boolean isInLogin = false;
        do {
            isInLogin = !isInLogin;
            if (isInLogin) {
                loginForm = new LoginForm(null);
            } else {
                registrationForm = new RegistrationForm(null);
            }
            System.out.println(loginForm.user);
        } while (loginForm.user == null && registrationForm.user==null);
        GamePanel gamePanel = getGamePanel(loginForm);
        gamePanel.startGameThread();
    }

}
