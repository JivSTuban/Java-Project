package Controles;

import Entities.Items.ItemSalve;
import GUI.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean wPressed,  aPressed,  sPressed,  dPressed, shiftPressed, pPressed = false ;
    public boolean pressed1, pressed2, pressed0;
    public boolean zPressed = true;
    public boolean addKey,giveBoots;
    public boolean openInventory = false;

    InventoryKeyHandler invHandler = new InventoryKeyHandler();
    VersusHandler verHandler = new VersusHandler();

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
        if (code == KeyEvent.VK_1 ){
            pressed1 = true;
            devMode = true;
            rst1++;
            if(rst1 == 2){
                pressed1 = false;
                devMode = false;
                rst2 = 0;
            }
        }
        if (code == KeyEvent.VK_2 ){
            pressed2 = true;
            rst2++;
            if(rst2 == 2){
                pressed2 = false;
                rst2 = 0;
            }
        }

        if (code == KeyEvent.VK_BACK_SLASH ){
            System.out.println("Scripts:\n-addkey \n-unlispeed\n-giveboots\n-givesalve\n-cancel");

            do{
                System.out.print("Enter addItem: "); String str = sc.nextLine();str = str.toLowerCase();
                if( str.equals("addkey") )   {addKey =true;   valid = true;}
                else if(str.equals("giveboots")) {giveBoots = true;   valid = true;}
                else if(str.equals("givesalve")) {
                    if(!gp.player.searchInventory("salve")) {
                        gp.player.inventory.add(new ItemSalve());
                    }

                    gp.player.salveCount += sc.nextInt();
                    valid = true;

                }
                //else if(str.equals("unlispeed"))  {maxDuration = 999999;   valid = true;}
                else if(str.equals("cancel"))  {valid = true;}

                else{
                    valid = !valid;
                    System.out.println("--Command not Found");
                }

            }while(!valid);
        }

        /*-------------------------------------------------------------------------------------------------------------
                                                  Inventory
         -------------------------------------------------------------------------------------------------------------*/
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
        invHandler.inventoryKeys(openInventory,code,gp);
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
            //pause
            if (code == KeyEvent.VK_P ){
                if(!pPressed){
                    pPressed =true;
                // if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                }
                //else if(gp.gameState == gp.pauseState){
                // gp.gameState = gp.playState;
                // }
            }

            // pause state
            if(code == KeyEvent.VK_P){
                    gp.gameState = gp.playState;
                }

            //dialogue state
            else if(gp.gameState == gp.dialogueState){
                if (code == KeyEvent.VK_Z){
                    gp.gameState = gp.playState;
                }
            }
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
        if(gp.gameState == gp.pauseState){

        }
        if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
           /*-------------------------------------------------------------------------------------------------------------
                                                  Versus Screen
         -------------------------------------------------------------------------------------------------------------*/
        if((code == KeyEvent.VK_Z && gp.gameState == gp.versusScreen) || zPressed) {
            zPressed = true;
            verHandler.versusKeys(gp, code);
        }
        if (code == KeyEvent.VK_X && gp.gameState == gp.versusScreen) {
            gp.gameState = gp.playState;
            zPressed = false;

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