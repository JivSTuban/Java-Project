package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ItemSalve extends SuperItem{
    public ItemSalve() {

        name = "salve";
        description = "Adds up 20 to your current HP";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/Salve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}


