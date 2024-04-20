package Entities;

import GUI.GamePanel;

public class NPC_Console extends Entity{
    public NPC_Console(GamePanel gp) {
        super(gp);
        NPC_name = "console";
        collision = true;
        direction = "left";
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/res/npc_console/entryrobot1");
        up2 = setup("/res/npc_console/entryrobot1");
        down1 = setup("/res/npc_console/entryrobot2");
        down2 = setup("/res/npc_console/entryrobot3");
        right1 = setup("/res/npc_console/entryrobot1");
        right2 = setup("/res/npc_console/entryrobot2");
        left1 = setup("/res/npc_console/entryrobot1");
        left2 = setup("/res/npc_console/entryrobot2");



    }
    public void setDialogue(){
        dialogues[0] = "Welcome to Mitsu Realm!";
        dialogues[1] = " \n\n Realm of Technology Veemax, the technomancer, deptly manipulates \nmachines and technology.";

    }
    public void speak(){
        gp.ui.currentDialogue = dialogues[0] + dialogues[1];
        switch (gp.player.direction){
            case "up":direction= "down";break;
            case "down":direction= "up";break;
            case "left":direction= "right";break;
            case "right":direction= "left";break;

        }
}
}
