package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemSalve extends SuperItem{
    public ItemSalve() {

        name = "salve";
        invLabel = "Mojitos ni zeke";
        description = "A bottle of mojitos with calamansi\nand salt. \n\nAdds up 20 to your current HP";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/invImgSalve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}


