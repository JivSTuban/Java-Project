package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HackingDevice extends SuperItem{
    public HackingDevice() {

        name = "hackingDevice";
        invLabel = "Hacking Device";
        description ="Opens up a Door";
        quantity = 0;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/hackingDevice.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
