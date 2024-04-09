package Entities;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_robot extends Entity{
    GamePanel gp;

    public  int screenX = 0;
    public  int screenY = 0;

    public NPC_robot(GamePanel gp){
        super(gp);
        collision = true;
        direction = "down";
        speed = 1;
        getImage();

    }

    public void getImage() {

        up1 = setup("/res/horse/down1");
        up2 = setup("/res/horse/down1");
        left1 = setup("/res/horse/down1");
        left2 = setup("/res/horse/down1");
        right1 = setup("/res/horse/down1");
        right2 = setup("/res/horse/down1");
        down1 = setup("/res/horse/down1");
        down2 = setup("/res/horse/down1");
    }
    public void setAction(){
        actionCounter++;

        if(actionCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
            if(i>50 && i<=75){
                direction = "left";
            }
            if(i>75 && i<=100){
                direction = "right";
            }
            actionCounter = 0;

        }
    }


}
