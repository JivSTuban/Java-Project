package Tile.world1;



import GUI.GamePanel;
import Tile.Tile;

public class OutsideTiles extends _SuperTile {


    public OutsideTiles(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/OutsideTile.txt");
    }

    public void getTileImage() {
        addTile(0,"tiles/outsideTile",false);
    }

}
