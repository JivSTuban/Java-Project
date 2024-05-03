package LoginRegister;


import GUI.GamePanel;


import java.io.IOException;
import java.sql.SQLException;

import static GUI.MainGameSettings.getGamePanel;

public class Starter {
    LoginForm loginForm = null;
    RegistrationForm registrationForm = new RegistrationForm();


    public Starter() throws SQLException, IOException, NullPointerException{

        loginForm = new LoginForm(null);

        while (loginForm.user == null ) {
            if (loginForm.rbtnc && !registrationForm.cancelled){
                registrationForm = new RegistrationForm(null);
                continue;
            }
            if (loginForm.triedLogin || registrationForm.cancelled) {
                    loginForm = new LoginForm(null);
                    if (loginForm.rbtnc){
                        registrationForm.cancelled = false;
                    }
            } else{
                registrationForm = new RegistrationForm(null);

            }
            System.out.println(loginForm.user);
        }
        GamePanel gamePanel = getGamePanel(loginForm);
        gamePanel.startGameThread();
    }

}
