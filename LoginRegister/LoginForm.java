package LoginRegister;


import Entities.Player;

import Users.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;


public class LoginForm extends JDialog {
    PreparedStatement preparedStatement;
    String query;
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mitsu_realm?serverTimezone=UTC", "root", "");
    private JPanel LoginPanel;
    private JTextField tfname;
    private JPasswordField pfpass;
    private JLabel lname;
    private JLabel lpass;
    private JButton loginbtn;
    private JButton registerbtn;
    public User user;
    User userFilled;
    boolean triedLogin = false, rbtnc = false;

    public User playerUser;
    private String username;
    public LoginForm(User user, String str) throws SQLException {
        playerUser = user;
    }
    public LoginForm(JFrame parent) throws IOException, SQLException, IllegalArgumentException {
        super(parent);
        setTitle("Mitsu Realm");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0); // Exit the program
            }
        });
        loginbtn.addActionListener(e -> {

            try {

                User user = loginUser();
                triedLogin = user == null;
                dispose();


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        registerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rbtnc = true;
                dispose();

            }
        });

        setVisible(true);
    }
    public int getItemCountInDatabase(String itemName) throws SQLException {
        int itemCount = 0;

        query = "SELECT COUNT(*) AS item_count FROM items WHERE Username = ? AND ItemName = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, itemName);

        // Execute the query and store the result in a ResultSet
        ResultSet resultSet = preparedStatement.executeQuery();

        // Retrieve the item count from the ResultSet
        if (resultSet.next()) {
            itemCount = resultSet.getInt("item_count");
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();

        return itemCount;
    }



    public void addItemToDatabase(String itemName) throws SQLException {
        query = "INSERT INTO items (ItemName, Username) VALUES (?, ?)";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, itemName);
        preparedStatement.setString(2, playerUser.username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void addEnemyKilledToDB(int index) throws SQLException {
        query = "INSERT INTO `enemy`(`index`, `username`) VALUES (?,?)";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, index);
        preparedStatement.setString(2, playerUser.username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void updateLocationToDB(String x, String y) throws SQLException {
        query = "UPDATE `users` SET WorldX = '" + x + "', WorldY = '" + y + "' WHERE username = '" + playerUser.username + "'";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void updateMoneyToDB(double x) throws SQLException {
        query = "UPDATE `users` SET Money = '" + x + "' WHERE username = '" + playerUser.username + "'";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public int lastX(User user) throws SQLException {
        int x = 0;
        query = "SELECT `WorldX` FROM `users` WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,user.username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            x = resultSet.getInt("WorldX"); // Get the value of WorldX column
        }

        resultSet.close();
        preparedStatement.close();

        return x;
    }
    public double lastMoney(User user) throws SQLException {
        double x = 0;
        query = "SELECT `Money` FROM `users` WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,user.username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            x = resultSet.getDouble("Money"); // Get the value of WorldX column
        }

        resultSet.close();
        preparedStatement.close();

        return x;
    }
    public int lastY(User user) throws SQLException {
        int y = 0;

        query = "SELECT `WorldY` FROM `users` WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            y = resultSet.getInt("WorldY"); // Get the value of WorldX column
        }
        resultSet.close();
        preparedStatement.close();

        return y;
    }
    public boolean isEnemyDead(int enemyIndex) throws SQLException {
        int indexCount = 0;

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(*) AS index_count FROM enemy WHERE `index` = ? AND username = ?")) {
            preparedStatement.setInt(1, enemyIndex);
            preparedStatement.setString(2, playerUser.username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    indexCount = resultSet.getInt("index_count");
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return indexCount > 0;
    }



    private User loginUser() throws SQLException, IOException {
        String username = tfname.getText();
        String password = pfpass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password are required.", "Try again", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        user = getUserFromDatabase(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the login form

            System.out.println(user.username);
            return user;
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Try again", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public User getUserFromDatabase(String username, String password) throws SQLException {

        userFilled = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mitsu_realm?serverTimezone=UTC", "root", "")) {
            String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userFilled = new User();
                        userFilled.username = resultSet.getString("Username");
                        userFilled.password = resultSet.getString("Password");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFilled;
    }

    public void addItemsToPlayer(Player player){
        query = "SELECT ItemName FROM items WHERE Username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, playerUser.username);
            System.out.println(playerUser.username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) { // Iterate through all rows returned by the query
                    String itemName = resultSet.getString("ItemName");
                    switch (itemName) {
                        case "Salve":
                            player.addToInventoryFromDatabase("salve", player);
                            player.salveCount++;
                            break;
                        case "AccessCard":
                            player.addToInventoryFromDatabase("AccessCard", player);
                            player.accessCard++;
                            break;
                        case "Boots":
                            player.addToInventory("boots");
                            player.gotBoots = true;
                            break;
                        // Add more cases as needed for other item types
                        default:
                            // Handle unrecognized item names or add additional cases
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}