package sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    Clip clip2;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/RoamTheme.wav");
        soundURL[1] = getClass().getResource("/sounds/BattleTheme.wav");
        soundURL[2] = getClass().getResource("/sounds/GameIntro.wav");
        soundURL[3] = getClass().getResource("/sounds/DrakeIntro.wav");
        soundURL[4] = getClass().getResource("/sounds/BobbyIntro.wav");
        soundURL[5] = getClass().getResource("/sounds/Knife.wav");
        soundURL[6] = getClass().getResource("/sounds/GunShot.wav");
        soundURL[7] = getClass().getResource("/sounds/GigaPunch.wav");
        soundURL[8] = getClass().getResource("/sounds/DropKick.wav");
        soundURL[9] = getClass().getResource("/sounds/FireFist.wav");
        soundURL[10] = getClass().getResource("/sounds/FrostFist.wav");
        soundURL[11] = getClass().getResource("/sounds/StartScreenMusic.wav");
        //gets music
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            //gets the music and opens it

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setFile2(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip2 = AudioSystem.getClip();
            clip2.open(ais);
            //gets the music and opens it

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void start(){
        clip.start();
    } //starts music
    public void start2(){clip2.start();} //starts soundeffect
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    } //loops music
    public void stop(){
        clip.stop();
    } // stops msuic
    public void stop2(){clip2.stop();} //stops soundeffect
}

