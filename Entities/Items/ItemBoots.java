package Entities.Items;

import GUI.GamePanel;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ItemBoots extends SuperItem{


    public ItemBoots() {

        name = "boots";
        invLabel = "British Boots";
        description ="Adds up 6 movement Speed";
        quantity = 0;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/Boots.png"));
        }catch(IOException e){
            e.printStackTrace();

        }
    }

}