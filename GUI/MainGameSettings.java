package GUI;

import LoginRegister.LoginForm;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public abstract class MainGameSettings {

    public abstract GamePanel getGamePanel(LoginForm loginForm) throws SQLException, IOException;
}
