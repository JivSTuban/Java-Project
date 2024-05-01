package Entities.PlayerSkills;

import GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Skill1 extends PlayerSkills{

    public Skill1(GamePanel gp) {
        this.gp = gp;
        skillName = "Pato";
        manaCost = 10;
        setSkillDamage(10);
        description = "this is skill 1 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;

    }
    public void update(){
        description = "this is skill 1 \nDamage:"+getSkillDamage()+"\nMana cost: "+manaCost;
    }




}
