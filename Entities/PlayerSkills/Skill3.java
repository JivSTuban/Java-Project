package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill3 extends PlayerSkills{


    public Skill3(GamePanel gp) {
        this.gp = gp;
        setSkillDamage( 30);
        skillName = "Stab";
        manaCost = 10;
        description = "Kill you enemy at the back \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
        isArea = true;


    }
    public void update(){
        description = "Kill you enemy at the back \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }

}
