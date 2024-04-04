package Tile;



import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
     GamePanel GP;
    Tile[] tiles;
    int mapTileNumber[][];

    public TileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[10];
        mapTileNumber = new int[GP.maxScreenCol][GP.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/map01.txt");
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Dungeon_24x24.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/GRASS+.png")));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/wall.png")));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/water.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            String line;
            while ((line = br.readLine()) != null && row <= GP.maxScreenRow){
                String[] nums = line.split(" ");
                for (int col = 0; col <= GP.maxScreenCol && col < nums.length; col++) {
                    int num = Integer.parseInt(nums[col]);
                    mapTileNumber[col][row] = num;
                }
                row++;
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){
        int tilenum = 0;
        for (int row = 0; row <= GP.maxScreenRow; row++) {
            for (int col = 0; col <= GP.maxScreenCol; col++) {
                int x = col * GP.tileSize;
                int y = row * GP.tileSize;

                if(row < GP.maxScreenRow && col < GP.maxScreenCol){
                    tilenum = mapTileNumber[col][row];
                }

                g2.drawImage(tiles[tilenum].image, x, y, GP.tileSize, GP.tileSize, null);
            }
        }
    }




}
