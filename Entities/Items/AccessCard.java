package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AccessCard extends SuperItem{

    public AccessCard() {

        name = "accessCard";
        description = "Open a door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/accessCard.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
