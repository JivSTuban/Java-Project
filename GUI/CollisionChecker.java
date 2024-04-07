package GUI;

import Entities.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity e){
        int entityLWorldX = e.worldX + e.solidArea.x;
        int entityRWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTWorldY = e.worldY + e.solidArea.y;
        int entityBWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int entityLC = entityLWorldX/gp.tileSize;
        int entityRC = entityRWorldX/gp.tileSize;
        int entityTR = entityTWorldY/gp.tileSize;
        int entityBR = entityBWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction){
            case"up":
                entityTR = (entityTWorldY - e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityTR];
                if(gp.collisionTileManger.tiles[tileNum1].collision == true || gp.collisionTileManger.tiles[tileNum2].collision == true){
                    e.collisionOn = true;
                }
                break;
            case"down":
                entityBR = (entityBWorldY + e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityBR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityBR];
                if(gp.collisionTileManger.tiles[tileNum1].collision == true || gp.collisionTileManger.tiles[tileNum2].collision == true){
                    e.collisionOn = true;
                }
                break;
            case"left":
                entityLC = (entityLWorldX - e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityLC][entityBR];
                if(gp.collisionTileManger.tiles[tileNum1].collision == true || gp.collisionTileManger.tiles[tileNum2].collision == true){
                    e.collisionOn = true;
                }
                break;
            case"right":
                entityRC = (entityRWorldX + e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityRC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityBR];
                if(gp.collisionTileManger.tiles[tileNum1].collision == true || gp.collisionTileManger.tiles[tileNum2].collision == true){
                    e.collisionOn = true;
                }
                break;

        }

    }
}
