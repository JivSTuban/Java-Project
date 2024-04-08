package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boots extends SuperItem{

    public Boots() {
        name = "boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/Boots.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
