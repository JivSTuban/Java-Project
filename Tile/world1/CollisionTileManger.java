package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;

public class CollisionTileManger extends _SuperTile {

    public CollisionTileManger(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/CollisionTile.txt");
    }
    public void getTileImage() {
        addTile(0,"tiles/Wall/0transparent",false);
        addTile(1,"tiles/Wall/1CornerWallLeft",true);
        addTile(2,"tiles/Wall/2CornerWallRight",true);
        addTile(3,"tiles/Wall/3EndingWallCLower",true);
        addTile(4,"tiles/Wall/4EndingWallCUpper",true);
        addTile(5,"tiles/Wall/5SideWallR",true);
        addTile(6,"tiles/Wall/6UpperCornerLeft",true);
        addTile(7,"tiles/Wall/7UpperCornerRight",true);
        addTile(8,"tiles/Wall/8WallMid",true);
        addTile(9,"tiles/Wall/9WallDownLeft",true);
        addTile(10,"tiles/Wall/10wallDownRight",true);
        addTile(11,"tiles/Wall/11SideWallL",true);
        addTile(12,"tiles/Wall/12WallMidCenter",true);
        addTile(13,"tiles/Wall/13WallMidL",true);
        addTile(14,"tiles/Wall/14SideWallC",true);
        addTile(15,"tiles/Wall/15EndingWallLowerLeft",true);
        addTile(16,"tiles/Wall/16EndingWallLowerRight",true);
        addTile(17,"tiles/Wall/17EndingWallUpperLeft",true);
        addTile(18,"tiles/Wall/18EndingWallUpperRight",true);
        addTile(19,"tiles/Wall/19Corner",true);
        addTile(20,"tiles/Wall/20UpperCornerRight2",true);
        addTile(21,"tiles/Wall/21WallMidR",true);
        addTile(22,"tiles/Wall/22WallMidL",true);
        addTile(23,"tiles/Wall/23UpperCornerLeft2",true);
        addTile(24,"tiles/Wall/24tesss",true);
    }

}

