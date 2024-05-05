package Entities;

import Controles.Cooldown;
import GUI.AssetSetter;
import GUI.GamePanel;

import java.util.Random;

public class NPC_OptimusKhai extends Entity{
    AssetSetter assetSetter = new AssetSetter(this.gp);
    Cooldown droneSpawCD = new Cooldown(1000);
    private boolean skill2Activated = false;


    public NPC_OptimusKhai(GamePanel gp){
        super(gp);
        direction = "left";
        NPC_name = "OptimusKhai";
        NPC_VSname = "Optimus Khai";
        NPC_getVSImgae = "/res/npc/NPCOptimusKhai/sprite_ok8";
        NPC_getVSGIF = "/res/npc/NPCOptimusKhai/Optimus-Khai-Skill-GIF";

        collision = true;
        direction = "down";
        getImage();
        type = 2;
        maxHP = 150;
        npcHp = maxHP;
        speed = 1;
        npcScaleX = gp.tileSize+400;
        npcScaleY = gp.tileSize+400;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 242;
        solidArea.height = 230;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();

        isEnemy = true;

    }
    @Override
    public void setSkills(){
//
//        if(!skill2Activated && droneHp == 30)
//            droneSpawn = false;

//        if(skill2Activated || droneHp >0){
//            if(turnCounter<5)
//                turnCounter++;
//            while(pickSkill >=40 && pickSkill<50){
//                pickSkill = rand.nextInt(1,100)+1;
//            }
//        }
        if(pickSkill <= 40){
            this.npcDamage = 30;
            npcSkillName = "Use Generics";
        }
        else if(pickSkill<=50){
            this.npcDamage = 0;
            skill2Activated = true;
            npcSkillName = "Inheritance Activated spawned 2 drones ";
            System.out.println("Spawned 2 Drones");
            droneSpawn = true;

        }
        else if(pickSkill<=65){
            this.npcDamage = 0;
            npcHp+=20;
           npcSkillName = "Abstract healing 20 HP ";
        }
        else if(pickSkill<=100){
            this.npcDamage = 0;
            npcSkillName = "Interface activated ";
            System.out.println("Interface activated");

        }
//        if(turnCounter < 5 ){
//            turnCounter =1;
//            skill2Activated = false;
//        }
//        if(droneHp <=0) {
//            droneHp = 30;
//            droneSpawn =false;
//            skill2Activated = false;
//            turnCounter = 1;
//        }
        skill2Activated = true;
        droneSpawn = true;

    }
//    @Override
//    public int getDamage(){
//        setSkills();
//        return npcDamage;
//    }
//    @Override
//    public String getSkillName(){
//        return npcSkillName;
//    }


    @Override
    public int getDamage() {
        pickSkill = rand.nextInt(1,100)+1;
        setSkills();
        System.out.println("The random Number is: "+pickSkill);
        return npcDamage;
    }

    @Override
    public String getSkillName() {
        return npcSkillName;
    }

    public void getImage() {

        up1 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        up2 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        right1 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        right2 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        left1 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        left2 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        down1 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
        down2 = setup("/res/npc/NPCOptimusKhai/sprite_ok8");
    }
    public void setDialogue(){
        dialogues[0] = "You hit the Drone \n You hit the Drone";
    }
    public void speak(){
        gp.ui.currentDialogue = dialogues[0];

    }
    @Override
    public void setAction(String name){
        direction = "left";
    }






}
