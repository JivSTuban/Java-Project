package Entities.PlayerSkills;

import GUI.GamePanel;

public class Skill3 extends PlayerSkills{


    public Skill3(GamePanel gp) {
        this.gp = gp;
        setSkillDamage( 30);
        skillName = "skill 3";
        manaCost = 10;
        description = "this is skill 3 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;


    }
    public void update(){
        description = "this is skill 3 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }

}
