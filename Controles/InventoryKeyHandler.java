package Controles;

import GUI.GamePanel;

import java.awt.event.KeyEvent;

public class InventoryKeyHandler {


    public void inventoryKeys(boolean openInventory, int code, GamePanel gp){
        if(openInventory) {
            if (code == KeyEvent.VK_UP) {
                if (gp.ui.slotRow != 0)
                    gp.ui.slotRow--;
            }
            if (code == KeyEvent.VK_DOWN) {
                if (gp.ui.slotRow != 3)
                    gp.ui.slotRow++;

            }
            if (code == KeyEvent.VK_LEFT) {
                if (gp.ui.slotCol != 0)
                    gp.ui.slotCol--;
            }
            if (code == KeyEvent.VK_RIGHT) {
                if (gp.ui.slotCol != 4)
                    gp.ui.slotCol++;
            }
            if (code == KeyEvent.VK_ESCAPE ) {
                openInventory = false;
                gp.gameState = gp.playState;
            }

            if (code == KeyEvent.VK_Z ) {
                if (gp.player.inventory.get(gp.ui.getItemIndexOnSlot()).name.equals("salve")) {
                    gp.player.playerHP += 20;
                    gp.player.salveCount--;

                }
                if (gp.player.salveCount == 0) {
                    gp.player.removeItem("salve");
                }
            }

        }

    }



}
