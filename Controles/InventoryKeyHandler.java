package Controles;

import GUI.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InventoryKeyHandler {

    public void inventoryKeys(boolean openInventory, int code, GamePanel gp) {
        if (openInventory) {

            if (code == KeyEvent.VK_UP && gp.ui.slotRow != 0) { gp.ui.slotRow--; gp.playSE(9);}
            if (code == KeyEvent.VK_DOWN && gp.ui.slotRow != 3)  {gp.ui.slotRow++; gp.playSE(9);}
            if (code == KeyEvent.VK_LEFT && gp.ui.slotCol != 0) {gp.ui.slotCol--; gp.playSE(9);};
            if (code == KeyEvent.VK_RIGHT && gp.ui.slotCol != 4)  {gp.ui.slotCol++; gp.playSE(9);};


            if (code == KeyEvent.VK_ESCAPE) {
                gp.keyH.openInventory = false;
                gp.gameState = gp.playState;
            }

            if (code == KeyEvent.VK_Z) {
                if (gp.player.inventory.get(gp.ui.getItemIndexOnSlot()).name.equals("salve") &&
                        (gp.player.getPlayerHP() != gp.player.maxHP)) {
                    if (gp.player.getPlayerHP() <= gp.player.maxHP - 20) {
                        gp.player.playerHP += 20;
                    } else {
                        gp.player.setPlayerHP(gp.player.maxHP);

                    }
                    (gp.player.inventory.get(gp.player.searchInventoryIndex("salve")).quantity)--;
                }
                if (gp.player.inventory.get(gp.ui.getItemIndexOnSlot()).name.equals("accessCard")){
                    (gp.player.inventory.get(gp.player.searchInventoryIndex("accessCard")).quantity)--;
                    gp.keyH.doorOpen = true;
                    gp.keyH.openInventory = false;
                    gp.gameState = gp.playState;

                }
                if (gp.player.inventory.get(gp.ui.getItemIndexOnSlot()).name.equals("hackingDevice")){
                    gp.gameState = gp.hackingState;
                }

                if (gp.player.searchInventory("salve") && gp.player.inventory.get(gp.player.searchInventoryIndex("salve")).quantity < 1) {
                    gp.player.removeItem("salve");
                }
                if (gp.player.searchInventory("accessCard") && gp.player.inventory.get(gp.player.searchInventoryIndex("accessCard")).quantity < 1) {
                    gp.player.removeItem("accessCard");
                }
            }
        }
    }
}
