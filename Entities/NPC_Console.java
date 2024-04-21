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
        up1 = setup("/res/npc_console/$robotback1");
        up2 = setup("/res/npc_console/$robotback2");
        down1 = setup("/res/npc_console/$robotfront1");
        down2 = setup("/res/npc_console/$robotfront2");
        right1 = setup("/res/npc_console/$robotright1");
        right2 = setup("/res/npc_console/$robotright2");
        left1 = setup("/res/npc_console/$robotleft1");
        left2 = setup("/res/npc_console/$robotleft2");



    }
    public void setDialogue(){
        dialogues[0] = "Welcome to Mitsu Realm!";
        dialogues[1] = " \n\n Realm of Technology Veemax, the technomancer, deptly \nmanipulates machines and technology.";
        dialogues[2] = " \n\n the technomancer, deptly manipulates \nmachines and technology.";
    }
    public void speak(){

        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

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
}
