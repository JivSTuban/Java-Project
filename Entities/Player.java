package Entities;

import Controles.Cooldown;
import Controles.KeyHandler;
import Entities.Items.*;
import Entities.PlayerSkills.*;
import GUI.GamePanel;
import LoginRegister.LoginForm;
import Users.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    public boolean devMode = false;
    public Cooldown printDelay = new Cooldown(3000);

    boolean draw = false;
    GamePanel GP;
    KeyHandler KH;

    int defaultSpeed = 3;
    public  int screenX = 0;
    public  int screenY = 0;
    public LoginForm loginForm;
    User user ;

    public ArrayList<SuperItem> inventory = new ArrayList<>();
    public ArrayList<PlayerSkills> skills = new ArrayList<>();


    public final int invetntorySize = 20;
    public int NPCCollision ;
    public int accessCardDropCount =0;



    /*-----------------------------------------------------------------------------------------------
                                                    Items
     -----------------------------------------------------------------------------------------------*/
    public boolean gotBoots = false; //Boots
    boolean justGotBoots = false;
    public int salveCount=0;
    public int accessCard = 0;
    public int bootsCD=0;

    public int hackingDevice = 0;
    /*-----------------------------------------------------------------------------------------------
                                          Player Stats
    -----------------------------------------------------------------------------------------------*/
    public int maxHP = 100;
    public int playerHP = maxHP;
    public int level = 1;
    public int maxMana = 100;
    public int mana = maxMana;
    public int exp = 0;
    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }
    public boolean doorCollision = false;

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

    public Player(GamePanel GP, KeyHandler KH, User user,LoginForm loginForm) throws SQLException {
        super(GP);
        this.loginForm = loginForm;
        this.GP = GP;
        this.KH = KH;
        this.user = user;

        screenX = GP.screenWidth/2;
        screenY = GP.screenHeight/2;
        solidArea = new Rectangle(9,17,55,60);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
        setDefault();
        getPlayerImage();

        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);

    }
    public void setDefault() throws SQLException {
//        worldX = GP.tileSize * 3;//kilid
//        worldY = GP.tileSize * 74;//ibabaw
        try{
            worldX = GP.tileSize * loginForm.lastX(user);
            worldY = GP.tileSize * loginForm.lastY(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
        setGold(loginForm.lastMoney(user));
        loginForm.addItemsToPlayer(this);
        mana = loginForm.lastMana(user);
        setPlayerHP(loginForm.lastHP(user));
        setSpeed(4);
        direction = "down";
    }

    /*-----------------------------------------------------------------------------------------------
                                       Setting Up Player Image
      -----------------------------------------------------------------------------------------------*/
    public void getPlayerImage(){

        up1 = setup("/res/PlayerImage/walkForward1");
        up2 = setup("/res/PlayerImage/walkForward2");
        left1 = setup("/res/PlayerImage/walkLeft1");
        left2 = setup("/res/PlayerImage/walkLeft2");
        down1 = setup("/res/PlayerImage/walkDown1");
        down2 = setup("/res/PlayerImage/walkDown2");
        right1 = setup("/res/PlayerImage/walkRight1");
        right2 = setup("/res/PlayerImage/walkRight2");

    }

    /*-----------------------------------------------------------------------------------------------
                                        Update scene
       -----------------------------------------------------------------------------------------------*/

    public void update(KeyHandler keyH){
        // updateSkills();
        if(gp.haveKnuckles)
            skill1.setSkillDamage(30);

        developerSettings(keyH);
//        if(keyH.pPressed)
//            gp.gameState = gp.pauseState;
//
//        if(gp.gameState != gp.pauseState){
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
                        setSpeed(7);
                    }
                    keyH.activateBoots = true;

                    if(!keyH.shiftPressed)
                        setSpeed(defaultSpeed);
                }


                if(devMode)System.out.println("x ="+Math.round((((float)worldX / (gp.tileSize)))) + "\ny = "+Math.round((float) (worldY+20.5 )/ (gp.tileSize)));
                //Check collision
                gp.toxinOn = false;//reset the toxin
                collisionOn = false;
                GP.collisionChecker.checkTile(this, true);//check the collision and toxin again

                int objIndex = GP.collisionChecker.checkObject(this,true);
                try{
                    pickUpItem(objIndex);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                //toxin

                NPCCollision = GP.collisionChecker.checkEntity(this,gp.npc);
                interactNPC(NPCCollision, keyH);


                if (!collisionOn ){
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
                gp.keyH.zPressed = false;

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
        //}


    }

    /*-----------------------------------------------------------------------------------------------
                                       PickUp Item
      -----------------------------------------------------------------------------------------------*/
    public void pickUpItem(int i) throws SQLException {
        if(i != 999){
            LoginForm loginForm = new LoginForm(user, "");
            String itemName = GP.objItem[i].name;
//            System.out.println(playerHP);
            switch (itemName){
                case "salve":
                    GP.objItem[i] = null;
                    addToInventory("salve");
                    addToInventory(itemName,1);
                    loginForm.addItemToDatabase("salve",i,1);
                    break;
                case "boots":
                    gotBoots = true;
                    justGotBoots = true;
                    GP.objItem[i] = null;
                    if(devMode)System.out.println(getSpeed());
                    inventory.add(new ItemBoots());
                    loginForm.addItemToDatabase("boots",i,1);
                    break;
                case "hackingDevice":
                    GP.objItem[i] = null;
                    addToInventory("hackingDevice");
                    addToInventory(itemName,1);
                    System.out.println("hackingDevice");
                    loginForm.addItemToDatabase("hackingDevice",i,1);
                    break;
                case "accessCard":
                    GP.objItem[i] = null;
                    if(devMode)System.out.println("got Access Card");
                    addToInventory("card");
                    addToInventory(itemName,1);
                    break;
                case "DoorClose":
                    //  if(searchInventoryIndex("accessCard") >-1){

                    if(searchInventory("accessCard") || searchInventory("hackingDevice")){
                        if(!gp.keyH.doorOpen)
                            gp.gameState  = gp.inventoryState;
                        if(gp.keyH.doorOpen){
                            GP.objItem[i] = null;
                            KH.doorOpen =false;
                        }

                        if(gp.player.searchInventory("accessCard")){
                            if( (gp.player.inventory.get(gp.player.searchInventoryIndex("accessCard")).quantity) == 0)
                                gp.player.removeItem("accessCard");
                        }
                    }

                    //}
                    else {
                        System.out.println("You need Access Card to open this Door");
                    }
                    break;
                case "chest" :
                    GP.objItem[i] = null;
                    if(randomizer() == 1){
                        addToInventory("card");
                        addToInventory("accessCard",1);
                        System.out.println("got card");
                        loginForm.addItemToDatabase("AccessCard",i,1);
                    }

                    else{
                        // (inventory.get(i).quantity)++;
                        addToInventory("salve");
                        addToInventory("salve",1);
                        System.out.println("got Salve");
                        loginForm.addItemToDatabase("salve",i,1);
                    }
                    break;

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
    public void interactNPC(int i,KeyHandler keyH){
        if(i!=999){
            // playerHP--;
//            if(printDelay.isOnCooldown() && !draw) {
//                gp.ui.drawToTalk(900, 500, 300, 100, 200);
//                draw = true;
//            }

            if(gp.npc[i].isEnemy && gp.npc[i].NPC_name != null)
                gp.gameState = gp.versusScreen;
            else {
                if(!printDelay.isOnCooldown())
                    printDelay.trigger();

                if (keyH.zPressed){
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                    gp.npc[i].dialoguesCd.trigger();
                    gp.keyH.zPressed = false;
                 }
            }

        }
    }

    /* --------------------------------------------------------------------
                              Extra methods
     --------------------------------------------------------------------*/

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

    /*-----------------------------------------------------------------------------------------------
                                 Inventory Setting
    -----------------------------------------------------------------------------------------------*/
    public void addToInventory(String name){
        if((!searchInventory(name))&& name.equals("salve")) {
            inventory.add(new ItemSalve());
        }
        if((!searchInventory("accessCard")) && name.equals("card")){
            inventory.add(new AccessCard());
        }
        if((!searchInventory(name)) && name.equals("boots")){
            inventory.add(new ItemBoots());
        }
        if((!searchInventory(name)) && name.equals("hackingDevice")){
            inventory.add(new HackingDevice());
        }
    }
    public void addToInventory(String name, int quantity){
        (inventory.get(searchInventoryIndex(name)).quantity)+=quantity;
    }
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
                return true;
            }
        }
        return false;
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
                                     DeveloperMOde
    -----------------------------------------------------------------------------------------------*/

    void developerSettings(KeyHandler keyH){
        bootsCD = (int)keyH.cd.timeRemaining();
        if(keyH.cd.timeRemaining() <1000){
            keyH.canUse = true;
        }
        if(keyH.pressed2)
            devMode = true;
        if(!keyH.pressed2)
            devMode = false;
        if(keyH.addKey) {
            addToInventory("card");
            (inventory.get(searchInventoryIndex("accessCard")).quantity)++;
            keyH.addKey = false;
        }
        if(keyH.giveBoots)
            gotBoots = true;
    }
    public void addToInventoryFromDatabase(String name, Player player){
        if(!player.searchInventory("salve")&& name.equals("salve")) {
            player.inventory.add(new ItemSalve());
        }
        if(!player.searchInventory("accessCard")){
            player.inventory.add(new AccessCard());
        }
        if(!player.searchInventory("boots") && name.equals("boots")){
            player.inventory.add(new ItemBoots());
            gotBoots = true;
            justGotBoots = true;
        }
        if(!player.searchInventory("hackingDevice") && name.equals("hackingDevice")){
            player.inventory.add(new HackingDevice());
        }
    }
    @Override
    public int getDamage(){return 0;}
    @Override
    public String getSkillName(){return "";}
    @Override
    public void setSkills(){}
}