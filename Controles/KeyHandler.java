package Controles;

import Entities.Player;
import GUI.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class KeyHandler implements KeyListener {
    public boolean wPressed,  aPressed,  sPressed,  dPressed, shiftPressed = false ;
    public boolean pressed1, pressed2, pressed0;
    public boolean addKey,giveBoots;
    public boolean valid = true;
    Scanner sc = new Scanner(System.in);
        public boolean activateBoots = false;
        int bootsDuration = 0,maxDuration = 10;
        int cd = 0, cdDuration = 100;
        public boolean devMode = false;

        boolean canUse = true;

        int rst1=0, rst2=0;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Developer Mode
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
            System.out.println("Scripts:\n-addkey \n-unlispeed\n-giveboots");

          do{
              System.out.print("Enter addItem: "); String str = sc.nextLine();str = str.toLowerCase();
              if( str.equals("addkey") )   addKey =true;
              else if(str.equals("giveboots")) giveBoots = true;
              else if(str.equals("unlispeed"))  maxDuration = 999999;
              else{
                  valid = !valid;
                  System.out.println("--Command not Found");
              }
          }while(!valid);
        }
        //end of devMode

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


          if(activateBoots){
              //method for Movement Speed
              if(canUse){
                  if (code == KeyEvent.VK_SHIFT  ){

                      shiftPressed = true;
                  }
              }
              //count the duration
              if(shiftPressed){
                  bootsDuration++;
                 if(this.devMode)
                      System.out.println( "Duration: "+bootsDuration);
              }
              //if duration is equal to 10 set movement speed to default
              if(bootsDuration == maxDuration){
                  canUse = false;
                  shiftPressed = false;
              }
              //start the cooldown
              if(!canUse){
                 cd++;
                if(this.devMode)
                    System.out.println("Cd: "+cd);

              }
              //check if Cooldown is done
              if(cd > cdDuration){
                  canUse = true;
                  cd = 0;
                  bootsDuration = 0;
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
