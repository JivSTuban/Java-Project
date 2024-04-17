package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorOpen extends SuperItem{
    public DoorOpen() {

        name = "DoorOpen";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Door/SDoorOpen.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}