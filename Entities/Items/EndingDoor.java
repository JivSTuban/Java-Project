package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EndingDoor extends SuperItem{
    public EndingDoor() {

        name = "EndingDoor";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Door/SDoorClose.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
