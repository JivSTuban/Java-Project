package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;
import Utility.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class CollisionTileManger extends _SuperTile {

    public CollisionTileManger(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/CollisionTile.txt");
    }
    public void getTileImage() {
        setUp(0,"tiles/Wall/0transparent",false);
        setUp(1,"tiles/Wall/1CornerWallLeft",true);
        setUp(2,"tiles/Wall/2CornerWallRight",true);
        setUp(3,"tiles/Wall/3EndingWallCLower",true);
        setUp(4,"tiles/Wall/4EndingWallCUpper",true);
        setUp(5,"tiles/Wall/5SideWallR",true);
        setUp(6,"tiles/Wall/6UpperCornerLeft",true);
        setUp(7,"tiles/Wall/7UpperCornerRight",true);
        setUp(8,"tiles/Wall/8WallMid",true);
        setUp(9,"tiles/Wall/9WallDownLeft",true);
        setUp(10,"tiles/Wall/10wallDownRight",true);
        setUp(11,"tiles/Wall/11SideWallL",true);
        setUp(12,"tiles/Wall/12WallMidCenter",true);
        setUp(13,"tiles/Wall/13WallMidL",true);
        setUp(14,"tiles/Wall/14SideWallC",true);
        setUp(15,"tiles/Wall/15EndingWallLowerLeft",true);
        setUp(16,"tiles/Wall/16EndingWallLowerRight",true);
        setUp(17,"tiles/Wall/17EndingWallUpperLeft",true);
        setUp(18,"tiles/Wall/18EndingWallUpperRight",true);
        setUp(19,"tiles/Wall/19Corner",true);
        setUp(20,"tiles/Wall/20UpperCornerRight2",true);
        setUp(21,"tiles/Wall/21WallMidR",true);
        setUp(22,"tiles/Wall/22WallMidL",true);
        setUp(23,"tiles/Wall/23UpperCornerLeft2",true);
        setUp(24,"tiles/Wall/24tesss",true);
    }

}

