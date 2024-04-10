package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Drone extends Entity{

    public NPC_Drone(GamePanel gp){
        super(gp);
        collision = true;
        direction = "down";
        speed = 1;
        getImage();

    }

    public void getImage() {

        up1 = setup("/res/npc/DroneUP");
        up2 = setup("/res/npc/DroneUp2");
        down1 = setup("/res/npc/DroneDown");
        down2 = setup("/res/npc/DroneDown2");
    }


}
