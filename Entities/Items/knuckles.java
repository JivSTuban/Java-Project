package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class knuckles extends SuperItem{
    public knuckles() {

        name = "knuckles";
        invLabel = "Mojitos ni zeke";
        description = "A bottle of mojitos with calamansi\nand salt. \n\nAdds up 20 to your current HP";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/invImgSalve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
