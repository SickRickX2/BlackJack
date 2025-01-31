
package model;

import javax.sound.sampled.*;
import java.io.*;

public class AudioManager {

    private static AudioManager instance;
    private Clip currentClip;

    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    private AudioManager() {
    }

    public void play(String filename) {
        stop(); // Stop any currently playing audio
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioIn);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    public void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }

    public void playMenuMusic() {
        play("res/audio/menu_music.wav");
    }

    public void playGameMusic() {
        play("res/audio/game_music.wav");
    }

    public void playWinMusic() {
        play("res/audio/win_music.wav");
    }

    public void playLoseMusic() {
        play("res/audio/lose_music.wav");
    }

    public void playTieMusic() {
        play("res/audio/lose_music.wav");
    }
}



