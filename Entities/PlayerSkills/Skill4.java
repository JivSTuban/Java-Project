package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill4 extends PlayerSkills{

    public Skill4(GamePanel gp) {
        this.gp = gp;
        setSkillDamage(40);
        skillName = "Wabalo";
        manaCost = 15;
        description = "Don't know  \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;

    }
    public void update(){
        description = "Don't know \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }
}
