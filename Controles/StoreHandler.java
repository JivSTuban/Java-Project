package Controles;

import GUI.GamePanel;

import java.awt.event.KeyEvent;

public class StoreHandler {

    public void openStoreHandler(boolean openStore, int code, GamePanel gp) {
        System.out.println("Working");

            if (code == KeyEvent.VK_UP && gp.ui.slotRow != 0) gp.ui.slotRow--;
            if (code == KeyEvent.VK_DOWN && gp.ui.slotRow != 3) gp.ui.slotRow++;
            if (code == KeyEvent.VK_LEFT && gp.ui.slotCol != 0) gp.ui.slotCol--;
            if (code == KeyEvent.VK_RIGHT && gp.ui.slotCol != 4) gp.ui.slotCol++;


            if (code == KeyEvent.VK_ESCAPE) {
                gp.keyH.openStore = false;
                gp.gameState = gp.playState;
            }

            if (code == KeyEvent.VK_Z) {
                if(gp.player.getGold() >= gp.storeItem.get(gp.ui.getItemIndexOnSlot()).price ) {
                    gp.player.addToInventoryFromStore(gp.storeItem.get(gp.ui.getItemIndexOnSlot()).name);
                    System.out.println("Item name in Store:" +gp.storeItem.get(gp.ui.getItemIndexOnSlot()).name);
                    gp.player.setGold(gp.player.getGold() - gp.storeItem.get(gp.ui.getItemIndexOnSlot()).price );
                    System.out.println("Player Gold = "+ gp.player.getGold());
                }
            }
        }

}
