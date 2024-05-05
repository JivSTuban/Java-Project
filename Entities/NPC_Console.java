package Entities;

import Controles.Cooldown;
import GUI.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NPC_Console extends Entity{

    public NPC_Console(GamePanel gp) {
        super(gp);
        NPC_name = "console";
        collision = true;
        direction = "up";
        npcScaleX = gp.tileSize;
        npcScaleY = gp.tileSize;
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


        dialogues[0] = "\nHello to our dear Player! \n\n        Welcome to Mitsu Realm, \n          " +
                "  Realm of Technology Veemax, the technomancer, deptly manipulates machines \n" +
                "            and technology. ";
        dialogues[1] = "\nC    A    U    T    I    O    N  !  !  ! \n\nBe aware of drones and toxins that you will encounter, Remember the boss is \nstill waiting somewhere in" +
                "the map. Take care of you're health or else you might \nregret it later!";
        dialogues[2] = "\nThere are some item drops you might find so use it well. \n\n-Salve is to heal yourself to 20hp" +
                "\n-Boots that can give you 8horse power speed, \n-Hacking Device to hack some systems" +
                "\n-Key gives you access to doors you can find it anywhere in the map";
        dialogues[3] = "\n\nThat's all for me,\n\n          Good Luck! Have Fun! and Hope you enjoy the game";
    }
    public void speak(){

        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        if(!dialoguesCd.isOnCooldown())
            dialogueIndex++;

    }

    @Override
    public void setSkills() {

    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getSkillName() {
        return "";
    }
}
