package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends SuperItem{
    public Chest() {

        name = "chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/chest.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
