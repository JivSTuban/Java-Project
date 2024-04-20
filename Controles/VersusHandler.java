package Controles;

import GUI.GamePanel;

import java.awt.event.KeyEvent;

public class VersusHandler {

    public void versusKeys(GamePanel gp, int code){
        if(gp.gameState == gp.versusScreen) {

                if (code == KeyEvent.VK_UP) {
                    if (gp.vsScreen.slotRow != 0)
                        gp.vsScreen.slotRow--;
                }
                if (code == KeyEvent.VK_DOWN) {
                    if (gp.vsScreen.slotRow < 1 )
                        gp.vsScreen.slotRow++;

                }
                if (code == KeyEvent.VK_LEFT) {
                    if (gp.vsScreen.slotCol != 0)
                        gp.vsScreen.slotCol--;
                }
                if (code == KeyEvent.VK_RIGHT) {
                    if (gp.vsScreen.slotCol < 1)
                        gp.vsScreen.slotCol++;
                }
           if(!gp.turnTimer.isOnCooldown()){

               if (code == KeyEvent.VK_ENTER ) {
                   gp.npc[gp.player.NPCCollision].setNpcHp(gp.npc[gp.player.NPCCollision].getNpcHp() -
                           gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage()); //return player damage
                   System.out.println("Skill Damage =  "+gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage());
                   System.out.println("Skill name: "+ gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).skillName);
                   System.out.println("Drone "+gp.player.NPCCollision+" Hp: " +gp.npc[gp.player.NPCCollision].getNpcHp());
                   gp.turnTimer.trigger();
               }
           }
        }
    }
}

