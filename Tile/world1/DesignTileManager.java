package Tile.world1;

import GUI.GamePanel;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DesignTileManager {
    GamePanel GP;
    public Tile[] tiles;
    public int mapTileNumber[][];

    public DesignTileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/design.txt");
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/0transparent.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/1capsuleTop.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/2capsuleBot.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/3toxin1.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/4toxin2.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/5trashCanTop.png")));
            tiles[5].collision = true;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/6trashCanBot.png")));
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/7DDoorCloseL.png")));
            tiles[7].collision = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/8DDoorCloseR.png")));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/9SDoorClose.png")));
            tiles[9].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Design/10SDoorOpen.png")));
            tiles[10].collision = true;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //new
    public void loadMap(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col =0;
            int row =0;

            while (col < GP.maxWorldCol && row < GP.maxWorldRow){
                String line = br.readLine();
                while(col<GP.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == GP.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GP.maxWorldCol && worldRow < GP.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * GP.tileSize;
            int worldY = worldRow * GP.tileSize;
            int screenX = worldX - GP.player.worldX + GP.player.screenX;
            int screenY = worldY - GP.player.worldY + GP.player.screenY;

            g2.drawImage(tiles[tileNum].image, screenX, screenY, GP.tileSize, GP.tileSize, null);
            worldCol++;

            if (worldCol == GP.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }


    }
}
