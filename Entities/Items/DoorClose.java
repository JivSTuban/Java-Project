package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorClose extends SuperItem{
    public DoorClose() {
        name = "DoorClose";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Door/SDoorClose.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
