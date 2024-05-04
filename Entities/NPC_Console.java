package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Console extends Entity {
    public NPC_Console(GamePanel gp) {
        super(gp);
        NPC_name = "console";
        collision = true;
        direction = "up";
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

    public void setDialogue() {
        dialogues[0] = "Welcome to Mitsu Realm!";
        dialogues[1] = " Realm of Technology Veemax, the technomancer, deptly \nmanipulates machines and technology.";
        dialogues[2] = " the technomancer, deptly manipulates \nmachines and technology.";
    }
    @Override
    public int getDamage(){return 0;}
    @Override
    public String getSkillName(){return "";}
    public void setSkills(){}

}