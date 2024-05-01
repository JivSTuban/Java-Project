package LoginRegister;

import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;

public class RegistrationForm extends JDialog {
    public User user;
    LoginForm loginForm;
    private JTextField tfname;
    private JButton cancelButton;
    private JButton registerButton;
    private JLabel lname;
    private JLabel lpass;
    private JLabel lconpass;
    private JPanel RegisterPanel;
    private JPasswordField pfpass;
    private JPasswordField pfconpass;

    public User getUsernameFromDatabase(String username) {
        User user = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mitsu_realm?serverTimezone=UTC", "root", "")) {
            String query = "SELECT * FROM users WHERE Username = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.username = resultSet.getString("Username");
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

    public RegistrationForm(JFrame parent) throws IOException {
        super(parent);
        setTitle("Mitsu Realm");
        setContentPane(RegisterPanel);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0); // Exit the program
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String username = tfname.getText();
        String password = new String(pfpass.getPassword()); // Get password from JPasswordField
        String confirmPassword = new String(pfconpass.getPassword()); // Get password from JPasswordField
        User sameUsername = getUsernameFromDatabase(username);

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sameUsername != null) {
            JOptionPane.showMessageDialog(this, "Username is already taken.", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(username, password);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register new user.", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    private User addUserToDatabase(String username, String password) {
        User user = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mitsu_realm?serverTimezone=UTC", "root", "");
            String insertUser = "INSERT INTO users (Username, Password) " + "VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertUser);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.username = username;
                user.password = password;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
