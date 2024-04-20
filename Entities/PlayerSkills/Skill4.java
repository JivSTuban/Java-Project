package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill4 extends PlayerSkills{

    public Skill4(GamePanel gp) {
        this.gp = gp;
        setSkillDamage(40);
        skillName = "skill 4";
        manaCost = 10;

        description = "this is skill 4 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;

    }
    public void update(){
        description = "this is skill 4 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }
}
