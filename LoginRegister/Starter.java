package LoginRegister;


import GUI.GamePanel;
import GUI.MainGameSettings;
import com.mysql.cj.log.Log;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;



public class Starter extends JFrame {
    LoginForm loginForm = null;
    RegistrationForm registrationForm = new RegistrationForm();


    public Starter() throws SQLException, IOException, NullPointerException{


        loginForm = new LoginForm(null);
        while (loginForm.user == null ) {
            if(registrationForm.user != null){
                loginForm = new LoginForm(null);
            }
            else if (loginForm.rbtnc && !registrationForm.cancelled){
                registrationForm = new RegistrationForm(null);
                continue;
            }
            else if (loginForm.triedLogin || registrationForm.cancelled) {
                    loginForm = new LoginForm(null);
                    if (loginForm.rbtnc){
                        registrationForm.cancelled = false;
                    }
            } else{
                registrationForm = new RegistrationForm(null);

            }
            System.out.println(loginForm.user);
        }
        GamePanel gamePanel = getGamePanel();
        gamePanel.startGameThread();

    }
    public Starter(LoginForm loginForm) throws SQLException, IOException {
        this.loginForm = loginForm;
        LoginForm loginForm1 = new LoginForm(loginForm.user, "");
        GamePanel gamePanel = getGamePanel();
        gamePanel.startGameThread();
    }

    public GamePanel getGamePanel() throws SQLException, IOException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("Mitsu Realm");
        GamePanel gamePanel = new GamePanel(loginForm.user, this, loginForm);
        add(gamePanel);
        pack();
        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int windowWidth = (int)screenSize.getWidth(); // window width
        int windowHeight = (int)screenSize.getHeight(); // window height
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        setBounds(x, y, windowWidth, windowHeight);

        setVisible(true);
        gamePanel.setupGame(loginForm);

        return gamePanel;

    }
    public void closeFrame(){
        dispose();
    }
}
