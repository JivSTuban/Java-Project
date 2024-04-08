package GUI;

import Entities.Items.Boots;
import Entities.Items.itemSalve;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem(){
        gp.objItem[0] =new itemSalve();
        gp.objItem[0].worldX = 4 * gp.tileSize;
        gp.objItem[0].worldY = 70 * gp.tileSize;

        gp.objItem[1] =new Boots();
        gp.objItem[1].worldX = 3 * gp.tileSize;
        gp.objItem[1].worldY = 76 * gp.tileSize;

        gp.objItem[2] =new Boots();
        gp.objItem[2].worldX = 10 * gp.tileSize;
        gp.objItem[2].worldY = 76 * gp.tileSize;


    }


}
