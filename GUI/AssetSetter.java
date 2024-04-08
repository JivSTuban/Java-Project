package GUI;

import Entities.Items.ItemBoots;
import Entities.Items.ItemSalve;

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
        gp.objItem[1].worldX = spawnRand() * gp.tileSize;
        gp.objItem[1].worldY = spawnRand() * gp.tileSize;

        gp.objItem[2] =new ItemBoots();
        gp.objItem[2].worldX =  spawnRand() * gp.tileSize;
        gp.objItem[2].worldY =  spawnRand() * gp.tileSize;


    }


}
