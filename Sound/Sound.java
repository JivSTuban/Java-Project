package Sound;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/_Sound/BG_music.wav");
        soundURL[1] = getClass().getResource("/res/_Sound/ToxinSE.wav");
        soundURL[2] = getClass().getResource("/res/_Sound/2defeatSfx.wav");
        soundURL[3] = getClass().getResource("/res/_Sound/3EncounterWithEnemy.wav");
        soundURL[4] = getClass().getResource("/res/_Sound/4FinalBossDefeated.wav");
        soundURL[5] = getClass().getResource("/res/_Sound/5HackingDeviceSfx.wav");
        soundURL[6] = getClass().getResource("/res/_Sound/6LaserSFX.wav");
        soundURL[7] = getClass().getResource("/res/_Sound/7LevelUp.wav");
        soundURL[8] = getClass().getResource("/res/_Sound/8OptimusKhai_SFX.wav");
        soundURL[9] = getClass().getResource("/res/_Sound/9SelectingItems.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
