package Tile.Versus;

import GUI.GamePanel;
import Tile.Tile;
import Tile.world1._SuperTile;

public class BackgroundTM extends SuperTile {

    public BackgroundTM(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/BackGroundTiles.txt");
    }
    public void getTileImage() {
        addTile(0,"tiles/Background/0bgTiles",false);
        addTile(1,"tiles/Background/1TileBridge",false);
        addToxin(2,"tiles/Background/2WaterMid",true);
        addToxin(3,"tiles/Background/3WaterUpper",true);
        addTile(4,"tiles/Background/4blankTileDesign",false);
        addTile(5,"tiles/Background/5TileDesign2",false);
    }
}
