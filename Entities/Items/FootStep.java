package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FootStep  extends SuperItem{
    public FootStep() {

        name = "FootStep";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/PlayerImage/footstepToxin.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}
