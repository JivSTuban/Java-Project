package Tile.world1;

import GUI.GamePanel;
import Tile.Tile;

public class DesignTileManager extends _SuperTile {


    public DesignTileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/design.txt");
    }
    public void getTileImage() {
        addTile(0,"tiles/Design/0transparent",false);
        addTile(1,"tiles/Design/capsuleTop",false);
        addTile(2,"tiles/Design/capsuleBot",true);
        addTile(3,"tiles/Design/3toxin1",false);
        addTile(4,"tiles/Design/4toxin2",false);
        addTile(5,"tiles/Design/5trashCanTop",false);
        addTile(6,"tiles/Design/6trashCanBot",true);
        addTile(7,"tiles/Design/7DDoorCloseL",true);
        addTile(8,"tiles/Design/8DDoorCloseR",true);
        addTile(9,"tiles/Design/9SDoorClose",true);
        addTile(10,"tiles/Design/10SDoorOpen",true);

    }

}
