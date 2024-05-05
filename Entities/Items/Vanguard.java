package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Vanguard extends SuperItem{
    public Vanguard() {

        name = "vanguard";
        invLabel = "Vanguard";
        description = "A bottle of mojitos with calamansi\nand salt. \n\nAdds up 20 to your current HP";
        price = 100;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Inventory/invImgSalve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
