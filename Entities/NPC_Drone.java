package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Drone extends Entity{

    public NPC_Drone(GamePanel gp){
        super(gp);
        NPC_name = "drone";
        NPC_VSname = "Drone";
        collision = true;
        direction = "own";
        NPC_getVSImgae = "/res/npc/NPCDrone/DroneVS";
        NPC_getVSGIF = "/res/npc/NPCDrone/DroneGIF";
        speed = 1;
        getImage();
        NPC_getSFX = 8;
        type = 2;
        maxHP = 50;
        npcHp = maxHP;
        npcScaleX = gp.tileSize;
        npcScaleY = gp.tileSize;


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();

        npcDamage = 10;
        isEnemy = true;

    }


    public void getImage() {

        up1 = setup("/res/npc/NPCDrone/DroneUP");
        up2 = setup("/res/npc/NPCDrone/DroneUp2");
        down1 = setup("/res/npc/NPCDrone/DroneDown");
        down2 = setup("/res/npc/NPCDrone/DroneDown2");
    }
    public void setDialogue(){
        dialogues[0] = "You hit the Drone \n You hit the Drone";
    }
    public void speak(){
        gp.ui.currentDialogue = dialogues[0];
        switch (gp.player.direction){
            case "up":
                direction= "down";
                break;
            case "down":
                direction= "up";
                break;
            case "left":
                direction= "right";
                break;
            case "right":
                direction= "left";
                break;

        }

    }
    @Override
    public int getDamage(){
        return npcDamage;
    }
    @Override
    public String getSkillName(){
        return "Basic Attack";
    }
    @Override
    public void setSkills(){}




}
