package GUI;

import Entities.Entity;
import Entities.Items.*;
import Entities.NPC_Drone;
import Entities.Toxin;

import java.lang.reflect.InvocationTargetException;
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

    public void setImage(int index, int x, int y, SuperItem item) {
        try {
            gp.objItem[index] = item.getClass().newInstance();
            gp.objItem[index].worldX = x * gp.tileSize;
            gp.objItem[index].worldY = y * gp.tileSize;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setItem() {

        setImage(0,spawnRand(),spawnRand(),  new ItemSalve());
        setImage(1,3,78,  new ItemBoots());
        setImage(2,spawnRand(),spawnRand(),  new ItemSalve());
        setImage(3,4,78,  new AccessCard());
        setImage(4,8,50,  new DoorOpen());
        setImage(5,8,50,  new DoorClose());
        setImage(6,2,19,  new DoorOpen());
        setImage(7,2,19,  new DoorClose());
        setImage(8,1,6,  new DoorOpen());
        setImage(9,1,6,  new DoorClose());

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

    public void setNPCChar(int index, int x, int y, Entity npc) {
        try {
            gp.npc[index] = npc.getClass().getDeclaredConstructor(GamePanel.class).newInstance(gp);
            gp.npc[index].worldX = x * gp.tileSize;
            gp.npc[index].worldY = y * gp.tileSize;
        } catch ( InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNPC() {
        setNPCChar(0, 34, 74, new NPC_Drone(gp));
        setNPCChar(1, 54, 65, new NPC_Drone(gp));
        setNPCChar(2, 6, 72, new NPC_Drone(gp));
        setNPCChar(3, 69, 77, new NPC_Drone(gp));
    }
}