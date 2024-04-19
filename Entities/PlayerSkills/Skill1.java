package Entities.PlayerSkills;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Skill1 extends PlayerSkills{

    public Skill1() {

        skillName = "DoorClose";
        setSkillDamage( rand.nextInt(1,10)+1);

    }



}
