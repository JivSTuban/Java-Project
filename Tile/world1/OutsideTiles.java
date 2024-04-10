package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;
import Utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class OutsideTiles extends _SuperTile {


    public OutsideTiles(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/OutsideTile.txt",GP);
    }

    public void getTileImage() {
        setUp(0,"tiles/outsideTile",false);
    }

}
