package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Drone extends Entity{
    GamePanel gp;

    public  int screenX = 0;
    public  int screenY = 0;



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
    public void setAction(){
        actionCounter++;

        if(actionCounter == 120){
            Random random = new Random();
            int i = random.nextInt(2)+1;

            if (i==1){
                holder++;
                if(holder==4)
                    direction = "down";
                else
                    direction = "up";

            }
            if(i==2){
                direction = "down";
                holder = 0;
            }

            actionCounter = 0;

        }

    }


}