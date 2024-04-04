package Tile;



import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
     GamePanel GP;
    Tile[] tiles;

    public TileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Dungeon_24x24.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/GRASS+.png")));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Dungeon_24x24.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        for (int row = 0; row <= GP.maxScreenRow; row++) {
            for (int col = 0; col <= GP.maxScreenCol; col++) {
                int x = col * GP.tileSize;
                int y = row * GP.tileSize;

                // Adjust x coordinate for right alignment
                int adjustedX = x + (GP.screenWidth - GP.maxScreenCol * GP.tileSize);

                g2.drawImage(tiles[1].image, adjustedX, y, GP.tileSize, GP.tileSize, null);
            }
        }
    }

}
