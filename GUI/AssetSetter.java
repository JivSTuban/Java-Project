package GUI;

import Entities.Items.*;
import Entities.Toxin;

import java.util.ArrayList;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();
    int i =0;
    ArrayList<Integer> posX= new ArrayList<>();
    ArrayList<Integer> posY= new ArrayList<>();

    public int spawnRand(){
        return rand.nextInt(78)+1;
    }


    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setItem(){
        gp.objItem[0] =new ItemSalve();
        gp.objItem[0].worldX = spawnRand() * gp.tileSize;
        gp.objItem[0].worldY = spawnRand() * gp.tileSize;

        gp.objItem[1] =new ItemBoots();
        gp.objItem[1].worldX = 3 * gp.tileSize;
        gp.objItem[1].worldY = 78 * gp.tileSize;

        gp.objItem[2] =new ItemBoots();
        gp.objItem[2].worldX =  spawnRand() * gp.tileSize;
        gp.objItem[2].worldY =  spawnRand() * gp.tileSize;

        gp.objItem[3] =new AccessCard();
        gp.objItem[3].worldX =  4 * gp.tileSize;
        gp.objItem[3].worldY =  78 * gp.tileSize;

        gp.objItem[4] =new DoorOpen();
        gp.objItem[4].worldX =  8 * gp.tileSize;
        gp.objItem[4].worldY =  50 * gp.tileSize;

        gp.objItem[5] =new DoorClose();
        gp.objItem[5].worldX =  8 * gp.tileSize;
        gp.objItem[5].worldY =  50 * gp.tileSize;


        gp.objItem[6] =new DoorOpen();
        gp.objItem[6].worldX =  2 * gp.tileSize;
        gp.objItem[6].worldY =  19 * gp.tileSize;

        gp.objItem[7] =new DoorClose();
        gp.objItem[7].worldX =  2 * gp.tileSize;
        gp.objItem[7].worldY =  19 * gp.tileSize;

        gp.objItem[8] =new DoorOpen();
        gp.objItem[8].worldX =  1 * gp.tileSize;
        gp.objItem[8].worldY =  6 * gp.tileSize;

        gp.objItem[9] =new DoorClose();
        gp.objItem[9].worldX =  1 * gp.tileSize;
        gp.objItem[9].worldY =  6 * gp.tileSize;

    }

    public void setToxin(){
        posX.add(11);
        posY.add(54);
        posX.add(10);
        posY.add(54);
        posX.add(10);
        posY.add(53);
        posX.add(9);
        posY.add(53);
        posX.add(9);
        posY.add(52);
        posX.add(7);
        posY.add(51);
        posX.add(7);
        posY.add(52);


        posX.add(38);
        posY.add(64);
        posX.add(38);
        posY.add(65);
        posX.add(39);
        posY.add(65);

        for(int y = 69 ;y!=74;y++) {
            posX.add(66);
            posY.add(y);
        }
        for(int y = 67 ;y!=74;y++) {
            posX.add(63);
            posY.add(y);
        }

        for(int y = 68 ;y!=74;y++) {
            posX.add(64);
            posY.add(y);
        }

        for(int y = 69 ;y!=74;y++) {
            posX.add(65);
            posY.add(y);
        }


        for(int x = 37 ;x!=40;x++) {
            posX.add(x);
            posY.add(66);
        }
        for(int x = 36 ;x!=41;x++) {
            posX.add(x);
            posY.add(67);
        }
        for(int x = 36 ;x!=42;x++) {
            posX.add(x);
            posY.add(68);
        }

        for(int i=0;i< posX.size();i++) {
            gp.toxins[i] = new Toxin();
            gp.toxins[i].worldX = posX.get(i) * gp.tileSize;
            gp.toxins[i].worldY = posY.get(i) * gp.tileSize;
        }


    }




}
