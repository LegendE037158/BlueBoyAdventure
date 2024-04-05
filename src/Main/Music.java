package Main;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {
    Clip clip;
    File soundURL[] = new File[30];

    public int volumeIndex = 10; // Ten by default
    FloatControl fc;

    public Music() {
        soundURL[0] = new File("res/Musics/BlueBoyAdventure.wav");
        soundURL[1] = new File("res/Musics/coin.wav");
        soundURL[2] = new File("res/Musics/powerup.wav");
        soundURL[3] = new File("res/Musics/unlock.wav");
        soundURL[4] = new File("res/Musics/fanfare.wav");
        soundURL[5] = new File("res/Musics/hitmonster.wav");
        soundURL[6] = new File("res/Musics/blocked.wav");
        soundURL[7] = new File("res/Musics/gameover.wav");
        soundURL[8] = new File("res/Musics/levelup.wav");
        soundURL[9] = new File("res/Musics/burning.wav");

    }
    public void SetFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(6f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setVolume() {
        switch (volumeIndex) {
            case 0: fc.setValue(-80f); break;
            case 1: fc.setValue(-72f); break;
            case 2 : fc.setValue(-64f); break;
            case 3: fc.setValue(-56f); break;
            case 4: fc.setValue(-48f); break;
            case 5: fc.setValue(-40f); break;
            case 6: fc.setValue(-32f); break;
            case 7: fc.setValue(-24f); break;
            case 8: fc.setValue(-16f); break;
            case 9: fc.setValue(-8f); break;
            case 10: fc.setValue(6f); break;
                
        }
    }
    public void volUp(){
        volumeIndex++;
        if (volumeIndex >= 11){
            volumeIndex = 0;
        }
        setVolume();
    }
    public void setVol(){
        setVolume();
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.close();
    }
}
