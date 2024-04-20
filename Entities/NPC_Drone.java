package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Drone extends Entity{

    public NPC_Drone(GamePanel gp){
        super(gp);
        NPC_name = "drone";
        collision = true;
        direction = "down";
        speed = 1;
        getImage();
        type = 2;
        maxHP = 50;
        npcHp = maxHP;


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
        npcDamge = 10;

    }


    public void getImage() {

        up1 = setup("/res/npc/DroneUP");
        up2 = setup("/res/npc/DroneUp2");
        down1 = setup("/res/npc/DroneDown");
        down2 = setup("/res/npc/DroneDown2");
    }
    public void setDialogue(){
        dialogues[0] = "You hit the Drone \n You hit the Drone";
    }
    public void speak(){
        gp.ui.currentDialogue = dialogues[0];
        switch (gp.player.direction){
            case "up":direction= "down";break;
            case "down":direction= "up";break;
            case "left":direction= "right";break;
            case "right":direction= "left";break;

        }

    }



}
