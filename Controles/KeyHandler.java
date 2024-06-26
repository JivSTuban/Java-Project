package Controles;

import Entities.Items.ItemSalve;
import GUI.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Scanner;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean wPressed,  aPressed,  sPressed,  dPressed, zPressed =false, shiftPressed = false, pPressed = false;
    public boolean pressed1, pressed2, spacePressed = false;
    public boolean addKey,giveBoots;
    public boolean openInventory = false;
    public boolean selectIp = false;
    InventoryKeyHandler invHandler = new InventoryKeyHandler();
    VersusHandler verHandler = new VersusHandler();
    StoreHandler storeHandler = new StoreHandler();
    public boolean doorOpen = false;
    public boolean isfight = false;
    int dialogueIndex=0;
    public boolean openStore = false;

    //UseItem


    public boolean valid = true;
    Scanner sc = new Scanner(System.in);
    public boolean activateBoots = false;

    public boolean devMode = false;
    //Boots Cooldown
    int bootsDuration = 0,maxDuration = 2;
    int cdDuration =10;
    public Cooldown cd = new Cooldown(cdDuration * 1000);
    public Cooldown duration = new Cooldown(maxDuration * 1000);

    public boolean canUse = true;

    int rst1=0, rst2=0;

    public KeyHandler (GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //test
        if (code == KeyEvent.VK_H) {
            gp.gameState = gp.hackingState;
        }
        if (code == KeyEvent.VK_B) {
           if(!openStore){
               gp.gameState = gp.buyState;
               openStore = true;
               storeHandler.openStoreHandler(true,code,gp);
           }
           else{
               gp.gameState = gp.playState;
               openStore = false;
           }
        }
        if (code == KeyEvent.VK_P ){
            if(!pPressed){
                pPressed =true;
                gp.gameState = gp.pauseState;
            }
            else{
                pPressed =false;
                gp.gameState = gp.playState;
            }

        }



        if (code == KeyEvent.VK_1) {
            pressed1 = true;
            devMode = true;
            rst1++;
            if (rst1 == 2) {
                pressed1 = false;
                devMode = false;
                rst2 = 0;
            }
        }
        if (code == KeyEvent.VK_2) {
            pressed2 = true;
            rst2++;
            if (rst2 == 2) {
                pressed2 = false;
                rst2 = 0;
            }
        }

        if (gp.gameState == gp.hackingState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_ENTER) {
                selectIp = true;
            }
            if (code == KeyEvent.VK_UP && gp.ui.hacking.slotRow != 0) gp.ui.hacking.slotRow--;
            if (code == KeyEvent.VK_DOWN && gp.ui.hacking.slotRow != 5) gp.ui.hacking.slotRow++;
            if (code == KeyEvent.VK_LEFT && gp.ui.hacking.slotCol != 0) gp.ui.hacking.slotCol--;
            if (code == KeyEvent.VK_RIGHT && gp.ui.hacking.slotCol != 7) gp.ui.hacking.slotCol++;

        }

        if (code == KeyEvent.VK_BACK_SLASH) {
            System.out.println("Scripts:\n-addkey \n-unlispeed\n-giveboots\n-givesalve\n-cancel");

            do {
                System.out.print("Enter addItem: ");
                String str = sc.nextLine();
                str = str.toLowerCase();
                if (str.equals("addkey")) {

                        gp.player.addToInventory("accessCard",99);

                    gp.player.inventory.get(gp.player.searchInventoryIndex("accessCard")).quantity += sc.nextInt();
                    valid = true;
                } else if (str.equals("giveboots")) {
                    giveBoots = true;
                    valid = true;
                } else if (str.equals("goto")) {
                    int y = sc.nextInt();
                    int x = sc.nextInt();
                  gp.player.worldY = y;
                  gp.player.worldX = x;
                    valid = true;
                }else if (str.equals("givesalve")) {
                    if (!gp.player.searchInventory("salve")) {
                        gp.player.inventory.add(new ItemSalve());
                    }

                    gp.player.salveCount += sc.nextInt();
                    valid = true;

                }
                //else if(str.equals("unlispeed"))  {maxDuration = 999999;   valid = true;}
                else if (str.equals("cancel")) {
                    valid = true;
                } else {
                    valid = !valid;
                    System.out.println("--Command not Found");
                }

            }while(!valid);
        }
            if(openInventory){
                invHandler.inventoryKeys(openInventory,code,gp);
            }
        if(openStore){
            storeHandler.openStoreHandler(true,code,gp);
        }
        /*-------------------------------------------------------------------------------------------------------------
                                                  Inventory
         -------------------------------------------------------------------------------------------------------------*/
        if (gp.gameState == gp.inventoryState)
            openInventory = true;

        if(code == KeyEvent.VK_I){
            if(!openInventory){
                openInventory =true;
                gp.gameState = gp.inventoryState;

            }
            else {
                openInventory =false;
                gp.gameState = gp.playState;
            }

        }

        /*-------------------------------------------------------------------------------------------------------------
                                                  Game State
         -------------------------------------------------------------------------------------------------------------*/
        if(gp.gameState == gp.playState){

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                wPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                aPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                sPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                dPressed = true;
            }
            if (code == KeyEvent.VK_Z){
                zPressed = true;
            }
            if (code == KeyEvent.VK_BACK_SPACE){
                spacePressed = true;
            }

            //pause


            //dialogue state

            if(activateBoots){
                //method for Movement Speed
                if(canUse){

                    if (code == KeyEvent.VK_SHIFT  ){
                        shiftPressed = true;
                    }

                    //count the duration
                    if(shiftPressed){
                        if(!duration.isOnCooldown()){
                            duration.trigger();

                        }
                        if (this.devMode) {
                            System.out.println("Used Time: " + duration.timeRemaining());
                        }
                        if (duration.timeRemaining() < 1000  ) {
                            if (this.devMode)
                                System.out.println("cooldown start");
                            canUse = false;
                            shiftPressed = false;
                        }
                    }
                }
                //start the cooldown
                if (!canUse) {
                    if(!cd.isOnCooldown())
                        cd.trigger();// Trigger the cooldown
                    if (this.devMode)
                        System.out.println(cd.timeRemaining()/1000+" sec");
                }
            }
        }
        else if(gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_Z){
                gp.gameState = gp.playState;
            }
        }

        if(gp.gameState == gp.dialogueState){
            if(code==KeyEvent.VK_SPACE){
                dialogueIndex++;
            }
        }
        if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
           /*-------------------------------------------------------------------------------------------------------------
                                                  Versus Screen
         -------------------------------------------------------------------------------------------------------------*/
//        if(gp.gameState == gp.versusScreen){
//            verHandler.versusKeys(gp, code);
//        }
        if(gp.gameState == gp.versusScreen){
            if((code == KeyEvent.VK_Z  || isfight)) {
                isfight = true;
                try {
                    verHandler.versusKeys(gp, code);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (code == KeyEvent.VK_X ) {
                gp.gameState = gp.playState;
                gp.npc[gp.player.NPCCollision].setNpcHp(gp.npc[gp.player.NPCCollision].maxHP);
                isfight = false;
                gp.sfxPlayed = false;

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            wPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            aPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            sPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            dPressed = false;
        }


        if (code == KeyEvent.VK_SHIFT){
            shiftPressed = false;

        }

    }
}