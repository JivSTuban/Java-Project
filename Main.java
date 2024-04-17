import GUI.GamePanel;
import Users.User;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;


class Main {
    public static void main(String[] args) {
//        RegistrationForm registrationForm = new RegistrationForm(null);
//        User user = registrationForm.user;
//        if(user == null){
//            System.out.println("Registration canceled.");
//            try{
//                registrationForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            }catch (Exception e){
//                System.out.println("Game Closed");
//                return;
//            }
//        }else{
//            System.out.println("Successful registration of: "+user.username+".");
            GamePanel gamePanel = getGamePanel();
            gamePanel.startGameThread();
        //}
    }

    private static GamePanel getGamePanel() {
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
        int windowWidth = 800; // window width
        int windowHeight = 600; // window height
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        window.setBounds(x, y, windowWidth, windowHeight);

        window.setVisible(true);
        gamePanel.setupGame();
        return gamePanel;
    }
}