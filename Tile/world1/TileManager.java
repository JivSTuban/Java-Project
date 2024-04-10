package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;
import Utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager extends _SuperTile {

    public TileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[13];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/BackGroundTiles.txt",GP);
    }
    public void getTileImage() {

            setUp(0,"tiles/Background/0bgTiles",false);
            setUp(1,"tiles/Background/1TileBridge",false);
            setUp(2,"tiles/Background/2WaterMid",false);
            setUp(3,"tiles/Background/3WaterUpper",false);
            setUp(4,"tiles/Background/4blankTileDesign",false);
            setUp(5,"tiles/Background/5TileDesign2",false);
    }
}
