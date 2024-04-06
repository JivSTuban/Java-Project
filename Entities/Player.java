package Entities;

import Controles.KeyHandler;
import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel GP;
    KeyHandler KH;


    public  int screenX = 0;
    public  int screenY = 0;


    public Player(GamePanel GP, KeyHandler KH) {


        this.GP = GP;
        this.KH = KH;

        screenX = GP.screenWidth/2;
        screenY = GP.screenHeight/2;
        solidArea = new Rectangle(8,16,32,32);
        setDefault();
        getPlayerImage();
    }
    public void setDefault(){
        worldX = GP.tileSize * 16;
        worldY = GP.tileSize * 12;
        setSpeed(3);
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/up2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/right2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blueKnight/down2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(KeyHandler keyH){
        if(keyH.wPressed || keyH.aPressed || keyH.sPressed || keyH.dPressed){
            if(keyH.wPressed){
                direction = "up";


            }
            else if(keyH.aPressed){
                direction = "left";


            }
            else if(keyH.sPressed){
                direction = "down";


            }
            else if(keyH.dPressed){
                direction = "right";

            }
            //Check collision
            collisionOn = false;
            GP.collisionChecker.checkTile(this);
            if (collisionOn == false){
                switch (direction){
                    case"up":
                        worldY -= getSpeed();
                        break;
                    case"down":
                        worldY += getSpeed();
                        break;
                    case"left":
                        worldX -= getSpeed();
                        break;
                    case"right":
                        worldX += getSpeed();
                        break;
                }
            }
            spriteCount++;
            if(spriteCount>12){
                if (spriteNum==1){
                    spriteNum=2;
                } else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCount = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, GP.tileSize, GP.tileSize);

        BufferedImage image = null;
        if(direction.equals("up")){
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
        }else if(direction.equals("left")){
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }

        }else if(direction.equals("down")){
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
        }else if(direction.equals("right")){
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, GP.tileSize, GP.tileSize, null    );
    }

}
