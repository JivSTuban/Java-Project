package GUI;

import Controles.Cooldown;
import Entities.Entity;

public class CollisionChecker {
    GamePanel gp;


    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity e){
        int entityLWorldX = e.worldX + e.solidArea.x ;
        int entityRWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTWorldY = e.worldY + e.solidArea.y;
        int entityBWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int entityLC = entityLWorldX/gp.tileSize;
        int entityRC = entityRWorldX/gp.tileSize;
        int entityTR = entityTWorldY/gp.tileSize;
        int entityBR = entityBWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        int tileNum3, tileNum4;
        int toxin1, toxin2;

        switch (e.direction){
            case"up":
                entityTR = (entityTWorldY - e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityTR];
                tileNum3 = gp.designTileManager.mapTileNumber[entityLC][entityTR];
                tileNum4 = gp.designTileManager.mapTileNumber[entityRC][entityTR];
                toxin1 = gp.tileManager.mapTileNumber[entityLC][entityTR];
                toxin2 = gp.tileManager.mapTileNumber[entityRC][entityTR];
                if((gp.collisionTileManger.tiles[tileNum1].collision  || gp.collisionTileManger.tiles[tileNum2].collision )||
                        (gp.designTileManager.tiles[tileNum3].collision  || gp.designTileManager.tiles[tileNum4].collision )){
                    e.collisionOn = true;
                }
                if(gp.tileManager.tiles[toxin1].toxin  || gp.tileManager.tiles[toxin2].toxin ){
                 gp.toxinOn = true;
                }
                break;
            case"down":
                entityBR = (entityBWorldY + e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityBR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityBR];
                tileNum3 = gp.designTileManager.mapTileNumber[entityLC][entityBR];
                tileNum4 = gp.designTileManager.mapTileNumber[entityRC][entityBR];
                toxin1 = gp.tileManager.mapTileNumber[entityLC][entityBR];
                toxin2 = gp.tileManager.mapTileNumber[entityRC][entityBR];
                if((gp.collisionTileManger.tiles[tileNum1].collision  || gp.collisionTileManger.tiles[tileNum2].collision )||
                        (gp.designTileManager.tiles[tileNum3].collision  || gp.designTileManager.tiles[tileNum4].collision )){
                    e.collisionOn = true;
                }
                if(gp.tileManager.tiles[toxin1].toxin  || gp.tileManager.tiles[toxin2].toxin ){
                    gp.toxinOn = true;
                }
                break;
            case"left":
                entityLC = (entityLWorldX - e.getSpeed())/(gp.tileSize);
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityLC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityLC][entityBR];
                tileNum3 = gp.designTileManager.mapTileNumber[entityLC][entityTR];
                tileNum4 = gp.designTileManager.mapTileNumber[entityLC][entityBR];
                toxin1 = gp.tileManager.mapTileNumber[entityLC][entityTR];
                toxin2 = gp.tileManager.mapTileNumber[entityLC][entityBR];
                if((gp.collisionTileManger.tiles[tileNum1].collision || gp.collisionTileManger.tiles[tileNum2].collision)||
                        (gp.designTileManager.tiles[tileNum3].collision  || gp.designTileManager.tiles[tileNum4].collision )){
                    e.collisionOn = true;
                }
                if(gp.tileManager.tiles[toxin1].toxin  || gp.tileManager.tiles[toxin2].toxin ){
                    gp.toxinOn = true;
                }
                break;
            case"right":
                entityRC = (entityRWorldX + e.getSpeed())/gp.tileSize;
                tileNum1 = gp.collisionTileManger.mapTileNumber[entityRC][entityTR];
                tileNum2 = gp.collisionTileManger.mapTileNumber[entityRC][entityBR];
                tileNum3 = gp.designTileManager.mapTileNumber[entityRC][entityTR];
                tileNum4 = gp.designTileManager.mapTileNumber[entityRC][entityBR];
                toxin1 = gp.tileManager.mapTileNumber[entityRC][entityTR];
                toxin2 = gp.tileManager.mapTileNumber[entityRC][entityBR];

                if((gp.collisionTileManger.tiles[tileNum1].collision  || gp.collisionTileManger.tiles[tileNum2].collision )||
                        (gp.designTileManager.tiles[tileNum3].collision  || gp.designTileManager.tiles[tileNum4].collision )){
                    e.collisionOn = true;
                }
                if(gp.tileManager.tiles[toxin1].toxin  || gp.tileManager.tiles[toxin2].toxin ){
                    gp.toxinOn = true;
                }
                break;

        }

    }


    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.objItem.length; i++) {
            if (gp.objItem[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gp.objItem[i].solidArea.x = gp.objItem[i].worldX + gp.objItem[i].solidArea.x;
                gp.objItem[i].solidArea.y = gp.objItem[i].worldY + gp.objItem[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed() - 5;
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed() - 5;
                        break;
                }

                if (entity.solidArea.intersects(gp.objItem[i].solidArea)) {
                    if (gp.objItem[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objItem[i].solidArea.x = gp.objItem[i].solidAreaDefaultX;
                gp.objItem[i].solidArea.y = gp.objItem[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public int checkToxin(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.toxins.length; i++) {
            if (gp.toxins[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gp.toxins[i].solidArea.x = gp.toxins[i].worldX + gp.toxins[i].solidArea.x;
                gp.toxins[i].solidArea.y = gp.toxins[i].worldY + gp.toxins[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed() - 5;
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed() - 5;
                        break;
                }

                if (entity.solidArea.intersects(gp.toxins[i].solidArea)) {
                    if (gp.toxins[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.toxins[i].solidArea.x = gp.toxins[i].solidAreaDefaultX;
                gp.toxins[i].solidArea.y = gp.toxins[i].solidAreaDefaultY;
            }
        }

        return index;
    }


    public int checkEntity (Entity entity, Entity[] target){
        int index = 999;

        for(int i= 0;i<target.length;i++){
            if(target[i] != null){

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":  entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "down": entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "left": entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                                index = i;

                        }
                        break;
                    case "right": entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(target[i].solidArea)){
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }

        return index;
    }



}


