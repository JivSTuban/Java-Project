package Entities.PlayerSkills;

import GUI.GamePanel;

import java.util.Random;

public class PlayerSkills {
    GamePanel gp;
    Random rand = new Random();

    private int skillDamage = 0;
    public int manaCost = 0;
    public String skillName = "";
    public String description = "";
    public boolean isArea = false;

    public int getSkillDamage() {
        return skillDamage;
    }

    public int getManaCost() {return manaCost;}

    public void setSkillDamage(int skillDamage) {this.skillDamage = skillDamage;}

}
