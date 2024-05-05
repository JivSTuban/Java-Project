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

    public void resetDB() throws SQLException {
            query = "UPDATE `users` SET WorldX = '"+3+"', WorldY = '" + 78 + "',Money = '"+100+"', mana='"+100+"',Health='"+100+"' WHERE username = '" + playerUser.username + "'";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
    }

    public void deleteItemsOnDB() throws SQLException {
        String query = "DELETE * FROM items WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, playerUser.username);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


 /* ******************************************************************************************************************************
                                                           ADD Methods
    ****************************************************************************************************************************** */

    public void addItemToDatabase(String itemName, int itemIndex, int quantity) throws SQLException {
        query = "INSERT INTO items (name,itemIndex,quantity, username) VALUES (?, ?, ?, ?)";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, itemName);
        preparedStatement.setInt(2, itemIndex);
        preparedStatement.setInt(3, quantity);
        preparedStatement.setString(4, playerUser.username);
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

    /* ******************************************************************************************************************************
                                                        Search Methods
    ****************************************************************************************************************************** */

    public boolean isItemAvailable(int itemIndex) throws SQLException {
        int indexCount = 0;

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(*) AS index_count FROM items WHERE `itemIndex` = ? AND username = ?")) {
            preparedStatement.setInt(1, itemIndex);
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
        query = "SELECT name FROM items WHERE Username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, playerUser.username);
            System.out.println(playerUser.username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) { // Iterate through all rows returned by the query
                    String itemName = resultSet.getString("name");
                    switch (itemName) {
                        case "salve":
                            player.addToInventoryFromDatabase("salve", player);
                            player.inventory.get(player.searchInventoryIndex("salve")).quantity= getItemQuantity("salve");
                            break;
                        case "accessCard":
                            player.addToInventoryFromDatabase("AccessCard", player);
                            player.inventory.get(player.searchInventoryIndex("accessCard")).quantity= getItemQuantity("accessCard");
                            break;
                        case "hackingDevice":
                            player.addToInventoryFromDatabase("hackingDevice", player);
                            break;
                        case "boots":
                            player.addToInventoryFromDatabase("boots",player);
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
//    public void addItemsToPlayer(){
//        query = "SELECT name FROM items WHERE Username = ?";
//    }



     /* ******************************************************************************************************************************
                                                           GET Methods
    ****************************************************************************************************************************** */

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
    public int lastHP(User user) throws SQLException {
        int y = 0;

        query = "SELECT `Health` FROM `users` WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            y = resultSet.getInt("Health"); // Get the value of WorldX column
        }
        resultSet.close();
        preparedStatement.close();

        return y;
    }
    public int getItemQuantity(String itemName) throws SQLException {
        int quantity = 0;

        // Using parameterized query to prevent SQL injection
        String query = "SELECT `quantity` FROM `items` WHERE `username` = ? AND `name` = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, playerUser.username);
        preparedStatement.setString(2, itemName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            quantity = resultSet.getInt("quantity"); // Get the value of quantity column
        }
        resultSet.close();
        preparedStatement.close();

        return quantity;
    }

    public int lastMana(User user) throws SQLException {
        int y = 0;

        query = "SELECT `mana` FROM `users` WHERE username = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) { // Move to the first row of the result set
            y = resultSet.getInt("mana"); // Get the value of WorldX column
        }
        resultSet.close();
        preparedStatement.close();

        return y;
    }

    /* ******************************************************************************************************************************
                                                           Update Methods
    ****************************************************************************************************************************** */

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
    public void updateHealthToDB(int x) throws SQLException {
        query = "UPDATE `users` SET Health = '" + x + "' WHERE username = '" + playerUser.username + "'";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void updateManaToDB(int x) throws SQLException {
        query = "UPDATE `users` SET mana = '" + x + "' WHERE username = '" + playerUser.username + "'";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


}