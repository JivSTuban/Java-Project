package Controles;

import GUI.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NewGameHandler {

    public void newGameKeys(boolean openInventory, int code, GamePanel gp) {
        if (openInventory) {

            if (code == KeyEvent.VK_LEFT && gp.ui.slotCol != 0) gp.ui.slotCol--;
            if (code == KeyEvent.VK_RIGHT && gp.ui.slotCol != 4)  gp.ui.slotCol++;


            if (code == KeyEvent.VK_ESCAPE) {
                gp.keyH.openInventory = false;
                gp.gameState = gp.playState;
            }

            if (code == KeyEvent.VK_Z) {
                if (gp.newGameOption.get(gp.ui.getItemIndexOnSlot()).equals("NewGame")){


                }
                if (gp.newGameOption.get(gp.ui.getItemIndexOnSlot()).equals("Exit")){

                }
            }
        }
    }
}
