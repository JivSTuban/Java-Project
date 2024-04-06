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
    public Tile[] tiles;
    public int mapTileNumber[][];

    public TileManager(GamePanel GP) {
        this.GP = GP;
        tiles = new Tile[13];
        mapTileNumber = new int[GP.maxWorldCol][GP.maxWorldRow];
        getTileImage();
        loadMap("/res/maps/BackGroundTiles.txt");
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Background/bgTiles.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Background/TileBridge.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Background/WaterMid.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/Background/WaterUpper.png")));




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


//    public void loadMap(String filepath){
//        try{
//            InputStream is = getClass().getResourceAsStream(filepath);
//            assert is != null;
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int row = 0;
//            String line;
//            while ((line = br.readLine()) != null && row <= GP.maxScreenRow){
//                String[] nums = line.split(" ");
//                for (int col = 0; col <= GP.maxScreenCol && col < nums.length; col++) {
//                    int num = Integer.parseInt(nums[col]);
//                    mapTileNumber[col][row] = num;
//                }
//                row++;
//            }
//            br.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


//    public void draw(Graphics2D g2){
//        int tilenum = 0;
//        for (int row = 0; row <= GP.maxScreenRow; row++) {
//            for (int col = 0; col <= GP.maxScreenCol; col++) {
//                int x = col * GP.tileSize;
//                int y = row * GP.tileSize;
//
//                if(row < GP.maxScreenRow && col < GP.maxScreenCol){
//                    tilenum = mapTileNumber[col][row];
//                }
//
//                g2.drawImage(tiles[tilenum].image, x, y, GP.tileSize, GP.tileSize, null);
//            }
//        }
//    }
    public void draw(Graphics2D g2){
        int worldCol =0;
        int worldRow =0;

        while(worldCol < GP.maxWorldCol && worldRow<GP.maxWorldRow){
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * GP.tileSize;
            int worldY = worldRow * GP.tileSize;
            int screenX = worldX - GP.player.worldX + GP.player.screenX;
            int screenY = worldY - GP.player.worldY + GP.player.screenY;

            g2.drawImage(tiles[tileNum].image, screenX, screenY, GP.tileSize, GP.tileSize,null);
            worldCol++;

            if(worldCol == GP.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }

    }





}
