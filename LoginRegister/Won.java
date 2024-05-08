package LoginRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class Won extends JFrame{
    private JPanel panel1;
    private JButton celebrateButton;

    public Won() {
        setSize(new Dimension(404, 404));
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0,0,404,404,30,30));
        celebrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
