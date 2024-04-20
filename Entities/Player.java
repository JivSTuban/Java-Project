package Entities;

import Controles.KeyHandler;
import Entities.Items.AccessCard;
import Entities.Items.ItemBoots;
import Entities.Items.ItemSalve;
import Entities.Items.SuperItem;
import Entities.PlayerSkills.*;
import GUI.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    public boolean devMode = false;

    GamePanel GP;
    KeyHandler KH;

    int defaultSpeed = 3;
    public  int screenX = 0;
    public  int screenY = 0;

    public ArrayList<SuperItem> inventory = new ArrayList<>();
    public ArrayList<PlayerSkills> skills = new ArrayList<>();
    public final int invetntorySize = 20;
    public int NPCCollision = -1;


    /*-----------------------------------------------------------------------------------------------
                                            Items
     -----------------------------------------------------------------------------------------------*/
    public boolean gotBoots = false; //Boots
    boolean justGotBoots = false;
    public int salveCount=0;
    public int accessCard = 0;
    public int bootsCD=0;
    /*-----------------------------------------------------------------------------------------------
                                          Player Stats
    -----------------------------------------------------------------------------------------------*/
    public int maxHP = 100;
    public int playerHP = maxHP;
    public int level = 1;
    public int mana = 100;
    public int exp = 0;
    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    /*-----------------------------------------------------------------------------------------------
                                              Player Skills
        -----------------------------------------------------------------------------------------------*/
    Skill1 skill1 = new Skill1(gp);
    Skill2 skill2 = new Skill2(gp);
    Skill3 skill3 = new Skill3(gp);
    Skill4 skill4 = new Skill4(gp);
    /*-----------------------------------------------------------------------------------------------
                                            Player Gold
     -----------------------------------------------------------------------------------------------*/
    private double gold  =  1000;
    public double getGold() {return gold;}
    public void setGold(double gold) {this.gold = gold;}

    /*-----------------------------------------------------------------------------------------------
                                            Constructor
     -----------------------------------------------------------------------------------------------*/

    public Player(GamePanel GP, KeyHandler KH) {
        super(GP);

        this.GP = GP;
        this.KH = KH;

        screenX = GP.screenWidth/2;
        screenY = GP.screenHeight/2;
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
        setDefault();
        getPlayerImage();

        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);

    }
    public void setDefault(){
        worldX = GP.tileSize * 3;//kilid
        worldY = GP.tileSize * 77;//ibabaw
        setSpeed(4);
        direction = "down";

    }

    /*-----------------------------------------------------------------------------------------------
                                       Setting Up Player Image
      -----------------------------------------------------------------------------------------------*/
    public void getPlayerImage(){
        up1=setup("/res/blueKnight/up1");
        up2=setup("/res/blueKnight/up2");
        left1=setup("/res/blueKnight/left1");
        left2=setup("/res/blueKnight/left2");
        down1=setup("/res/blueKnight/down1");
        down2=setup("/res/blueKnight/down2");
        right1=setup("/res/blueKnight/right1");
        right2=setup("/res/blueKnight/right2");
    }
    /*-----------------------------------------------------------------------------------------------
                                     Inventory Setting
    -----------------------------------------------------------------------------------------------*/
    public void removeItem (String name){
            for(int i=0;i<inventory.size();i++){
                if(inventory.get(i).name.equals(name)){
                    inventory.remove(i);
                    break;
                }
            }

    }
    public boolean searchInventory(String name){
        for (SuperItem superItem : inventory) {
            if (superItem.name.equals(name)) {
                return false;

            }
        }
        return true;
    }
    public int searchInventoryIndex(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            SuperItem superItem = inventory.get(i);
            if (superItem.name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /*-----------------------------------------------------------------------------------------------
                                        Update scene
       -----------------------------------------------------------------------------------------------*/
    public void update(KeyHandler keyH){
       // updateSkills();
        updateInventoryCount();

                                                     //--Developer Mode

        bootsCD = (int)keyH.cd.timeRemaining();
        if(keyH.cd.timeRemaining() <1000){
            keyH.canUse = true;
        }
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


            if(devMode)System.out.println("x ="+Math.round((((float) worldX /(GP.tileSize)+1))) + "\ny = "+Math.round((((float) worldY /(GP.tileSize)+1))));
            //Check collision
            gp.toxinOn = false;//reset the toxin
            collisionOn = false;
            GP.collisionChecker.checkTile(this);//check the collision and toxin again

            int objIndex = GP.collisionChecker.checkObject(this,true);
                pickUpItem(objIndex);
            //toxin

            NPCCollision = GP.collisionChecker.checkEntity(this,gp.npc);
                interactNPC(NPCCollision, keyH);


            if (!collisionOn){
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
    /*-----------------------------------------------------------------------------------------------
                                       PickUp Item
      -----------------------------------------------------------------------------------------------*/
    public void pickUpItem(int i){
        if(i != 999){
            String itemName = GP.objItem[i].name;
//            System.out.println(playerHP);
            switch (itemName){
                case "salve":
                    GP.objItem[i] = null;
                    salveCount++;
                    if(searchInventory("salve")){
                        inventory.add(new ItemSalve());
                    }
                    // System.out.println(playerHP);
                    break;
                case "boots":
                    gotBoots = true;
                    justGotBoots = true;
                    GP.objItem[i] = null;
                    if(devMode)System.out.println(getSpeed());
                    inventory.add(new ItemBoots());
                    break;
                case "accessCard":
                    GP.objItem[i] = null;
                    if(devMode)System.out.println("got Access Card");
                    accessCard++;
                    addToInventory("card");
                    System.out.println("Card count: "+accessCard);
                    break;
                case "DoorClose":
                    if(accessCard!=0){
                        GP.objItem[i] = null;
                        accessCard--;
                        if(accessCard == 0){
                            removeItem("accessCard");
                        }
                        System.out.println("Card count: "+accessCard);
                    }
                    else {
                        System.out.println("You need Access Card to open this Door");
                    }
                    break;
                case "chest" :
                    GP.objItem[i] = null;
                    if(randomizer() == 1){
                        accessCard++;
                        addToInventory("card");
                        System.out.println("got card");
                    }

                    else{
                        salveCount++;
                        addToInventory("salve");
                        System.out.println("got Salve");
                    }

            }

        }
    }
    /*-----------------------------------------------------------------------------------------------
                                     Draw Player Image
    -----------------------------------------------------------------------------------------------*/
    public void draw(Graphics2D g2){

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

    public int randomizer(){
        Random rand =new Random();
        return rand.nextInt(2)+1;
    }
    public void interactNPC(int i, KeyHandler keyH){
        if(i!=999){
           // playerHP--;

            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
                gp.gameState = gp.versusScreen;

        }
    }

    /* --------------------------------------------------------------------
                              Extra methods
     --------------------------------------------------------------------*/
    void addToInventory(String name){
        if(searchInventory("salve")&& name.equals("salve")) {
            inventory.add(new ItemSalve());
        }
        if(searchInventory("accessCard") && name.equals("card")){
            inventory.add(new AccessCard());
        }
    }
    void updateSkills(){
        skill1.setSkillDamage(10);
        skill2.setSkillDamage(20);
        skill3.setSkillDamage(30);
        skill4.setSkillDamage(40);

        skill1.update();
        skill2.update();
        skill3.update();
        skill4.update();
    }
    void updateInventoryCount(){
        if(!inventory.isEmpty()) {
            if (searchInventoryIndex("salve") != -1)
                inventory.get(searchInventoryIndex("salve")).count = salveCount;
            if (searchInventoryIndex("accessCard") != -1)
                inventory.get(searchInventoryIndex("accessCard")).count = accessCard;
        }
    }

}