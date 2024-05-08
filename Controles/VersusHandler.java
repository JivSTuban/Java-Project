package Controles;

import GUI.GamePanel;

import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class VersusHandler {

    public void versusKeys(GamePanel gp, int code) throws SQLException {
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
//                   gp.sfxPlayed = false;
                   if(gp.player.mana >= gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getManaCost()){ //check if mana is enough
                       gp.player.mana -= gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getManaCost();
                        gp.player.loginForm.updateManaToDB(gp.player.mana);

                        if(gp.player.NPCCollision == 50) {
                            if(gp.npc[gp.player.NPCCollision].droneSpawn){
                                int splitDamage = gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage();
                                gp.npc[gp.player.NPCCollision].setNpcHp(gp.npc[gp.player.NPCCollision].getNpcHp() - splitDamage/2);
                                gp.npc[gp.player.NPCCollision].droneHp -= splitDamage/2;

                            }else{
                                gp.npc[gp.player.NPCCollision].setNpcHp(gp.npc[gp.player.NPCCollision].getNpcHp() -
                                        gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage());
                            }

                        }else{
                            gp.npc[gp.player.NPCCollision].setNpcHp(gp.npc[gp.player.NPCCollision].getNpcHp() -
                                    gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage());
                        }

                       System.out.println("(Player) -- Skill Damage =  "+gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).getSkillDamage());
                       System.out.println("(Player) -- name: "+ gp.player.skills.get(gp.vsScreen.getSkillIndexOnSlot()).skillName);
                       System.out.println("(Enemy) -- "+gp.npc[gp.player.NPCCollision].NPC_VSname+" "+gp.player.NPCCollision+" Hp: " +gp.npc[gp.player.NPCCollision].getNpcHp());
                       gp.turnTimer.trigger();
                   }

               }
           }
        }
    }
}

