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

public class CollisionTileManger {
    GamePanel GP;
    public Tile[] tiles;
    public int mapTileNumber[][];

    public CollisionTileManger(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[25];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/world1/CollisionTile.txt");
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/0transparent.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/1CornerWallLeft.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/2CornerWallRight.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/3EndingWallCLower.png")));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/4EndingWallCUpper.png")));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/5SideWallR.png")));
            tiles[5].collision = true;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/6UpperCornerLeft.png")));
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/7UpperCornerRight.png")));
            tiles[7].collision = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/8WallMid.png")));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/9WallDownLeft.png")));
            tiles[9].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/10wallDownRight.png")));
            tiles[10].collision = true;

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/11SideWallL.png")));
            tiles[11].collision = true;

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/12WallMidCenter.png")));
            tiles[12].collision = true;

            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/13WallMidL.png")));
            tiles[13].collision = true;

            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/14SideWallC.png")));
            tiles[14].collision = true;

            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/15EndingWallLowerLeft.png")));
            tiles[15].collision = true;

            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/16EndingWallLowerRight.png")));
            tiles[16].collision = true;

            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/17EndingWallUpperLeft.png")));
            tiles[17].collision = true;

            tiles[18] = new Tile();
            tiles[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/18EndingWallUpperRight.png")));
            tiles[18].collision = true;

            tiles[19] = new Tile();
            tiles[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/19Corner.png")));
            tiles[19].collision = true;

            tiles[20] = new Tile();
            tiles[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/20UpperCornerRight2.png")));
            tiles[20].collision = true;

            tiles[21] = new Tile();
            tiles[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Wall/21WallMidR.png")));
            tiles[21].collision = true;

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
