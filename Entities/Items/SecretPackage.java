package Entities.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SecretPackage extends SuperItem{
    public SecretPackage(){

        name = "sPackage";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/Wall/11SideWallL.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }
}
