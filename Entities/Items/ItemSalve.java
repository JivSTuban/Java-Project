package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemSalve extends SuperItem{
    public ItemSalve() {
        name = "salve";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/Salve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}


