package Entities.PlayerSkills;

import java.util.Random;

public class PlayerSkills {
    Random rand = new Random();
    private int skillDamage = 0;
     String skillName = "";
    public int getSkillDamage() {
        return skillDamage;
    }

    public void setSkillDamage(int skillDamage) {
        this.skillDamage = skillDamage;
    }
}
