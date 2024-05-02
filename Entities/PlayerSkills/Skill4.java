package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill4 extends PlayerSkills{

    public Skill4(GamePanel gp) {
        this.gp = gp;
        setSkillDamage(40);
        skillName = "Wabalo";
        manaCost = 15;

        description = "this is skill 4 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;

    }
    public void update(){
        description = "this is skill 4 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }
}
