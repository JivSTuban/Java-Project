package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;

public class TileManager extends _SuperTile {

    public TileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[13];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/BackGroundTiles.txt");
    }
    public void getTileImage() {

            addTile(0,"tiles/Background/0bgTiles",false);
            addTile(1,"tiles/Background/1TileBridge",false);
            addTile(2,"tiles/Background/2WaterMid",false);
            addTile(3,"tiles/Background/3WaterUpper",false);
            addTile(4,"tiles/Background/4blankTileDesign",false);
            addTile(5,"tiles/Background/5TileDesign2",false);
    }
}
