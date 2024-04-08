package Entities.Items;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class itemSalve extends SuperItem{

    public itemSalve() {
        name = "salve";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/Salve.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
