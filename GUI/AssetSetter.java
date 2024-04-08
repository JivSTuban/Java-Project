package GUI;

import Entities.Items.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();

    public int spawnRand(){
        return rand.nextInt(78)+1;
    }


    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem(){
        gp.objItem[0] =new ItemSalve();
        gp.objItem[0].worldX = spawnRand() * gp.tileSize;
        gp.objItem[0].worldY = spawnRand() * gp.tileSize;

        gp.objItem[1] =new ItemBoots();
        gp.objItem[1].worldX = 3 * gp.tileSize;
        gp.objItem[1].worldY = 78 * gp.tileSize;

        gp.objItem[2] =new ItemBoots();
        gp.objItem[2].worldX =  spawnRand() * gp.tileSize;
        gp.objItem[2].worldY =  spawnRand() * gp.tileSize;

        gp.objItem[3] =new AccessCard();
        gp.objItem[3].worldX =  4 * gp.tileSize;
        gp.objItem[3].worldY =  78 * gp.tileSize;

        gp.objItem[4] =new DoorOpen();
        gp.objItem[4].worldX =  8 * gp.tileSize;
        gp.objItem[4].worldY =  50 * gp.tileSize;

        gp.objItem[5] =new DoorClose();
        gp.objItem[5].worldX =  8 * gp.tileSize;
        gp.objItem[5].worldY =  50 * gp.tileSize;


        gp.objItem[6] =new DoorOpen();
        gp.objItem[6].worldX =  2 * gp.tileSize;
        gp.objItem[6].worldY =  19 * gp.tileSize;

        gp.objItem[7] =new DoorClose();
        gp.objItem[7].worldX =  2 * gp.tileSize;
        gp.objItem[7].worldY =  19 * gp.tileSize;


//        gp.objItem[8] =new AccessCard();
//        gp.objItem[8].worldX =  5 * gp.tileSize;
//        gp.objItem[8].worldY =  78 * gp.tileSize;



    }




}
