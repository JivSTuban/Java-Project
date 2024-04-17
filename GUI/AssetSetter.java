package GUI;

import Entities.Entity;
import Entities.Items.*;
import Entities.NPC_Drone;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();

    public int spawnRand() {
        return rand.nextInt(78) + 1;
    }

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setImage(int index, int x, int y, SuperItem item,boolean collision) {
        try {
            gp.objItem[index] = item.getClass().newInstance();
            gp.objItem[index].worldX = x * gp.tileSize;
            gp.objItem[index].worldY = y * gp.tileSize;
            gp.objItem[index].collision = collision;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setItem() {

        setImage(0,spawnRand(),spawnRand(),  new ItemSalve(), false);
        setImage(1,3,78,  new ItemBoots(), false);
        setImage(2,spawnRand(),spawnRand(),  new ItemSalve(),false);
        setImage(3,4,78,  new AccessCard(),false);
        setImage(4,8,50,  new DoorOpen(),false);
        setImage(5,8,50,  new DoorClose(),true);
        setImage(6,2,19,  new DoorOpen(),false);
        setImage(7,2,19,  new DoorClose(),true);
        setImage(8,1,6,  new DoorOpen(),false);
        setImage(9,1,6,  new DoorClose(),true);
        setImage(9,4,75,  new Chest(),false);

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