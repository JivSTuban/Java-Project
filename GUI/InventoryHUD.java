package GUI;

import Entities.Items.AccessCard;
import Entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class InventoryHUD {

    GamePanel gp;
    Graphics2D g2;
    Font arial_16;
    Font arial_10;
    BufferedImage invHud;

    UI ui ;


    public InventoryHUD(GamePanel gp, UI ui ) {
        this.gp = gp;
        this.ui = ui;
        arial_16 = new Font("Arial", Font.PLAIN,16);
        arial_10 = new Font("Arial", Font.PLAIN,10);


    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        drawInventoryScreen();
    }
    private void hudBg(){
        ui.drawSubWindow(1450, 12, 450,970,230);

    }
    private void drawInventoryScreen() {
        hudBg();
        try {
            invHud = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/InventoryHUD.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        g2.drawImage(invHud, 1450, 12, 450,970,null);
    }

}
