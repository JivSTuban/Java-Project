package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill2 extends PlayerSkills{

    public Skill2(GamePanel gp) {
        this.gp = gp;
        setSkillDamage(20);
        skillName = "IP Man punch";
        manaCost = 30;

        description = "this is skill 2 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;

    }
    public void update(){
        description = "this is skill 2 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }
}
