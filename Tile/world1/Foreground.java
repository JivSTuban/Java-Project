package Tile.world1;

import GUI.GamePanel;
import Tile.Tile;

public class Foreground extends _SuperTile {


    public Foreground(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/Foreground.txt");
    }
    public void getTileImage() {
        addTile(0,"tiles/Design/0transparent",false);
        //addTile(0,"tiles/foreground/0transparent",false);

    }

}
