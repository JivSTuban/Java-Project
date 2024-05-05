package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Clarity extends SuperItem{
    public Clarity() {

        name = "clarity";
        invLabel = "Clarity";
        description = "A bottle of mojitos with calamansi\nand salt. \n\nAdds up 20 to your current HP";
        price = 30;
        quantity = 0;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/invImgSalve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
