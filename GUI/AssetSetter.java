package GUI;

import Entities.Items.*;
import Entities.NPC_Drone;
import Entities.Toxin;

import java.util.ArrayList;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();
    int i = 0;
    ArrayList<Integer> posX = new ArrayList<>();
    ArrayList<Integer> posY = new ArrayList<>();

    public int spawnRand() {
        return rand.nextInt(78) + 1;
    }


    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem() {
        gp.objItem[0] = new ItemSalve();
        gp.objItem[0].worldX = spawnRand() * gp.tileSize;
        gp.objItem[0].worldY = spawnRand() * gp.tileSize;

        gp.objItem[1] = new ItemBoots();
        gp.objItem[1].worldX = 3 * gp.tileSize;
        gp.objItem[1].worldY = 78 * gp.tileSize;

        gp.objItem[2] = new ItemBoots();
        gp.objItem[2].worldX = spawnRand() * gp.tileSize;
        gp.objItem[2].worldY = spawnRand() * gp.tileSize;

        gp.objItem[3] = new AccessCard();
        gp.objItem[3].worldX = 4 * gp.tileSize;
        gp.objItem[3].worldY = 78 * gp.tileSize;

        gp.objItem[4] = new DoorOpen();
        gp.objItem[4].worldX = 8 * gp.tileSize;
        gp.objItem[4].worldY = 50 * gp.tileSize;

        gp.objItem[5] = new DoorClose();
        gp.objItem[5].worldX = 8 * gp.tileSize;
        gp.objItem[5].worldY = 50 * gp.tileSize;


        gp.objItem[6] = new DoorOpen();
        gp.objItem[6].worldX = 2 * gp.tileSize;
        gp.objItem[6].worldY = 19 * gp.tileSize;

        gp.objItem[7] = new DoorClose();
        gp.objItem[7].worldX = 2 * gp.tileSize;
        gp.objItem[7].worldY = 19 * gp.tileSize;

        gp.objItem[8] = new DoorOpen();
        gp.objItem[8].worldX = 1 * gp.tileSize;
        gp.objItem[8].worldY = 6 * gp.tileSize;

        gp.objItem[9] = new DoorClose();
        gp.objItem[9].worldX = 1 * gp.tileSize;
        gp.objItem[9].worldY = 6 * gp.tileSize;

    }

    public void setToxin() {
        int[][] positions = {
                {11, 54}, {10, 54}, {10, 53}, {9, 53}, {9, 52}, {7, 51}, {7, 52},
                {38, 64}, {38, 65}, {39, 65},
                {66, 69}, {63, 67}, {64, 68}, {65, 69},
                {37, 66}, {36, 67}, {36, 68},
                {66, 69}, {66, 70}, {66, 71}, {66, 72}, {66, 73},
                {65, 69}, {65, 70}, {65, 71}, {65, 72}, {65, 73},
                {64, 69}, {64, 70}, {64, 71}, {64, 72}, {64, 73},
                {63, 69}, {63, 70}, {63, 71}, {63, 72}, {63, 73},
                {68, 69}, {68, 70},
                {69, 70}, {68, 70},

        };
        for (int i = 0; i < positions.length; i++) {
            gp.toxins[i] = new Toxin();
            gp.toxins[i].worldX = positions[i][0] * gp.tileSize;
            gp.toxins[i].worldY = positions[i][1] * gp.tileSize;
        }

    }

    public void setNPC(){
        gp.npc[0] = new NPC_Drone(gp);
        gp.npc[0].worldX = gp.tileSize*34;
        gp.npc[0].worldY = gp.tileSize*74;

        gp.npc[1] = new NPC_Drone(gp);
        gp.npc[1].worldX = gp.tileSize*33;
        gp.npc[1].worldY = gp.tileSize*74;

        gp.npc[2] = new NPC_Drone(gp);
        gp.npc[2].worldX = gp.tileSize*33;
        gp.npc[2].worldY = gp.tileSize*74;

        gp.npc[3] = new NPC_Drone(gp);
        gp.npc[3].worldX = gp.tileSize*34;
        gp.npc[3].worldY = gp.tileSize*74;
    }
}