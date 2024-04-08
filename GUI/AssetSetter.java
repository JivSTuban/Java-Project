package GUI;

import Entities.Items.ItemBoots;
import Entities.Items.ItemSalve;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem(){
        gp.objItem[0] =new ItemSalve();
        gp.objItem[0].worldX = 5 * gp.tileSize;
        gp.objItem[0].worldY = 78 * gp.tileSize;

        gp.objItem[1] =new ItemBoots();
        gp.objItem[1].worldX = 3 * gp.tileSize;
        gp.objItem[1].worldY = 76 * gp.tileSize;

        gp.objItem[2] =new ItemBoots();
        gp.objItem[2].worldX = 10 * gp.tileSize;
        gp.objItem[2].worldY = 76 * gp.tileSize;


    }


}
