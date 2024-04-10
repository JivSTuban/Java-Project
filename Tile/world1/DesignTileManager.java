package Tile.world1;

import GUI.GamePanel;
import Tile.Tile;
import Utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class DesignTileManager extends _SuperTile {


    public DesignTileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/design.txt");
    }
    public void getTileImage() {
        setUp(0,"tiles/Design/0transparent",false);
        setUp(1,"tiles/Design/1capsuleTop",true);
        setUp(2,"tiles/Design/2capsuleBot",true);
        setUp(3,"tiles/Design/3toxin1",false);
        setUp(4,"tiles/Design/4toxin2",false);
        setUp(5,"tiles/Design/5trashCanTop",true);
        setUp(6,"tiles/Design/6trashCanBot",true);
        setUp(7,"tiles/Design/7DDoorCloseL",true);
        setUp(8,"tiles/Design/8DDoorCloseR",true);
        setUp(9,"tiles/Design/9SDoorClose",true);
        setUp(10,"tiles/Design/10SDoorOpen",true);

    }

}
