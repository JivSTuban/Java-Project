package Entities;

import GUI.GamePanel;

import java.util.Random;

public class NPC_Veemax extends Entity{

    public NPC_Veemax(GamePanel gp){
        super(gp);
        NPC_name = "Veemax";
        NPC_VSname = "Veemax";
        collision = true;
        direction = "down";
        speed = 1;
        getImage();
        type = 2;
        maxHP = 150;
        npcHp = maxHP;
        npcScaleX = gp.tileSize+100;
        npcScaleY = gp.tileSize+100;
        NPC_getVSImgae = "/res/npc/NPCOptimusKhai/sprite_ok8";
        NPC_getVSGIF = "/res/npc/NPCOptimusKhai/Optimus-Khai-Skill-GIF";

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 142;
        solidArea.height = 130;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
        npcDamage = 10;
        isEnemy = true;

    }
    @Override
    public void setSkills(){
        Random rand = new Random();
        int pickSkill = rand.nextInt(1,100)+1;
        if(pickSkill <= 60){
            npcDamage = rand.nextInt(20,30)+1;//basic attack
            npcSkillName = "Basic attack";
        }
        else if(pickSkill<=90){
            npcDamage = rand.nextInt(30,45)+1;//Queue
            npcSkillName = "Queue";
        }
        else if(pickSkill <= 100){
            npcDamage = rand.nextInt(50,60)+1;//Link List
            npcSkillName = "LINKED LIST";
        }

    }
    @Override
    public int getDamage(){
        pickSkill = rand.nextInt(1,100)+1;
        setSkills();
        System.out.println("The random Number is: "+pickSkill);
        return npcDamage;
    }
    @Override
    public String getSkillName(){
        return npcSkillName;
    }


    public void getImage() {

        up1 = setup("/res/npc/Veemax/sprite_Veemax1");
        up2 = setup("/res/npc/Veemax/sprite_Veemax1");
        right1 = setup("/res/npc/Veemax/sprite_Veemax1");
        right2 = setup("/res/npc/Veemax/sprite_Veemax1");
        down1 = setup("/res/npc/Veemax/sprite_Veemax1");
        down2 = setup("/res/npc/Veemax/sprite_Veemax1");
        left1 = setup("/res/npc/Veemax/sprite_Veemax1");
        left2 = setup("/res/npc/Veemax/sprite_Veemax1");
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
