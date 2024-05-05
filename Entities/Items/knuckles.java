package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class knuckles extends SuperItem{
    public knuckles() {

        name = "knuckles";
        invLabel = "knuckles";
        description = "A bottle of mojitos with calamansi\nand salt. \n\nAdds up 20 to your current HP";
        price = 50;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/invImgSalve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
