import GUI.GamePanel;
import Users.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static GUI.MainGameSettings.getGamePanel;

public class LoginForm extends JDialog {
    private JPanel LoginPanel;
    private JTextField tfname;
    private JPasswordField pfpass;
    private JLabel lname;
    private JLabel lpass;
    private JButton loginbtn;
    private JButton registerbtn;
    public User user;
    public LoginForm(JFrame parent) throws IOException {
        super(parent);
        setTitle("Mitsu Realm");
        setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icons/Mitsu.png"))));
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        registerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    RegistrationForm registrationForm = new RegistrationForm(null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }
    private void loginUser() {
        String username = tfname.getText();
        String password = pfpass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password are required.", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = getUserFromDatabase(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the login form
            GamePanel gamePanel = getGamePanel();
            gamePanel.startGameThread();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUserFromDatabase(String username, String password) {
        User user = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mitsurealmdb?serverTimezone=UTC", "root", "")) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.username = resultSet.getString("Username");
                        user.password = resultSet.getString("Password");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}