package Entities;

import Controles.KeyHandler;
import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    public boolean devMode = false;
    GamePanel GP;
    KeyHandler KH;


    int defaultSpeed = 3;
    public  int screenX = 0;
    public  int screenY = 0;
    int chest = 0;
    //Items
    public boolean gotBoots = false;
    boolean justGotBoots = false;
    public int accessCard = 0;
    //playerStats
    public int playerHP = 100;
    public final int maxHP = 100;

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public Player(GamePanel GP, KeyHandler KH) {


        this.GP = GP;
        this.KH = KH;

        screenX = GP.screenWidth/2;
        screenY = GP.screenHeight/2;
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefault();
        getPlayerImage();
    }
    public void setDefault(){
        worldX = GP.tileSize * 7;
        worldY = GP.tileSize * 78;
        setSpeed(4);
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
        //Developer Mode
        if(keyH.pressed2)
            devMode = true;
        if(!keyH.pressed2)
            devMode = false;
        if(keyH.addKey) {
            accessCard++;
            keyH.addKey = false;
            System.out.println("card count: "+accessCard);
        }
        if(keyH.giveBoots)
          gotBoots = true;


        if(keyH.wPressed || keyH.aPressed || keyH.sPressed || keyH.dPressed || keyH.shiftPressed){
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

            if(gotBoots){
                if(keyH.shiftPressed){
                    setSpeed(9);
                }

                keyH.activateBoots = true;

                if(!keyH.shiftPressed)
                    setSpeed(defaultSpeed);
            }

           if(devMode)System.out.println("x ="+((worldX/(GP.tileSize-1))) + "\ny = "+((worldY/(GP.tileSize-1))));
            //Check collision
            collisionOn = false;
            GP.collisionChecker.checkTile(this);
            int objIndex = GP.collisionChecker.checkObject(this,true);
            pickUpItem(objIndex);

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
    public void pickUpItem(int i){
        if(i != 999){
            String itemName = GP.objItem[i].name;
//            System.out.println(playerHP);
            switch (itemName){
                case "salve": ;
                    if(playerHP < maxHP){
                        if(playerHP > 100){
                            setPlayerHP(100);
                        }
                        else{
                            setPlayerHP(getPlayerHP() + 25);
                        }

                    }
                    GP.objItem[i] = null;
                   // System.out.println(playerHP);
                    break;
                case "boots":
                    gotBoots = true;
                    justGotBoots = true;
                    GP.objItem[i] = null;
                    if(devMode)System.out.println(getSpeed());
                    break;
                case "accessCard":
                    GP.objItem[i] = null;
                    if(devMode)System.out.println("got Access Card");
                    accessCard++;
                    System.out.println("Card count: "+accessCard);
                    break;
                case "DoorClose":
                 if(accessCard!=0){
                     GP.objItem[i] = null;
                     accessCard--;
                     System.out.println("Card count: "+accessCard);
                 }
                 else {
                     System.out.println("You need Access Card to open this Door");
                 }


                    break;

            }

        }
    }
    public void toxin(int i){
        if(i != 999){
            String itemName = GP.objItem[i].name;
            System.out.println(playerHP);
            switch (itemName){
                case "salve": ;
                    if(playerHP < maxHP){
                        if(playerHP > 100){
                            setPlayerHP(100);
                        }
                        else{
                            setPlayerHP(getPlayerHP() + 25);
                        }

                    }
                    GP.objItem[i] = null;
                    System.out.println(playerHP);
                    break;
                case "boots":
                    gotBoots = true;
                    GP.objItem[i] = null;
                    System.out.println(getSpeed());
                    break;

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
