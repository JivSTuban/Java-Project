package LoginRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.SQLException;

public class Ded extends JFrame {
    private JPanel panel1;
    private JButton retryButton;
    private JButton exitButton;
    public boolean retried = false;

    public Ded(LoginForm loginForm) {

        setSize(new Dimension(550, 400));
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0,0,550,400,30,30));
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    retried = true;
                try {
                    loginForm.resetDB(loginForm.user);
                    loginForm.deleteItemsOnDB();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginForm.resetDB(loginForm.user);
                    loginForm.deleteItemsOnDB();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);

            }
        });
    }
}
