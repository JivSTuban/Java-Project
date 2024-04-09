/*package Entities;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC_robot extends Entity{

    public NPC_robot(GamePanel gp){
        super(gp);

        direction = "down";
        setSpeed(2);

        getImage();
    }
    public void getImage(){
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

    public void setAction(){
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

        }
        public void draw(Graphics2D g2){

        }
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
*/