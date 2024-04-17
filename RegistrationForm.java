import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog {
    public User user;
    final String DB_URL = "jdbc:mysql://localhost/mitsu_realm?serverTimezone=UTC";
    final String DB_USER = "root";
    final String DB_PASS = "";
    private JTextField tfname;
    private JTextField tfpass;
    private JTextField tfconpass;
    private JButton cancelButton;
    private JButton registerButton;
    private JLabel lname;
    private JLabel lpass;
    private JLabel lconpass;
    private JPanel RegisterPanel;

    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(RegisterPanel);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setVisible(true);
    }

    private void registerUser() {
         String username = tfname.getText();
         String password = tfpass.getText();
         String confirmPassword = tfconpass.getText();

         if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
             JOptionPane.showMessageDialog(this, "All fields are required.", "Try again", JOptionPane.ERROR_MESSAGE);
             return;
         }

         if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Try again", JOptionPane.ERROR_MESSAGE);
         }

         user = addUserToDatabase(username, password);
         if (!(user == null)){
             dispose();
         }else{
             JOptionPane.showMessageDialog(this, "Failed to register new user.", "Try again", JOptionPane.ERROR_MESSAGE);
         }
    }

    private User addUserToDatabase(String username, String password) {
        User user = null;
        try{
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            String insertUser = "INSERT INTO users (Username, Password) " + "VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertUser);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.username = username;
                user.password = password;
            }
            stmt.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
