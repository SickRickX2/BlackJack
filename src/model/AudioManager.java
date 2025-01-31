package model;

import javax.sound.sampled.*;
import java.io.*;

/**
 * The AudioManager class is responsible for managing audio playback in the application.
 * It supports playing, stopping, and looping audio clips.
 */
public class AudioManager {

    private static AudioManager instance;
    private Clip currentClip;

    /**
     * Returns the singleton instance of the AudioManager.
     *
     * @return the singleton instance of the AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private AudioManager() {
    }

    /**
     * Plays the audio file specified by the filename.
     * Stops any currently playing audio before starting the new one.
     *
     * @param filename the path to the audio file to be played
     */
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

    /**
     * Stops the currently playing audio, if any.
     */
    public void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }

    /**
     * Plays the menu music.
     */
    public void playMenuMusic() {
        play("res/audio/menu_music.wav");
    }

    /**
     * Plays the game music.
     */
    public void playGameMusic() {
        play("res/audio/game_music.wav");
    }

    /**
     * Plays the win music.
     */
    public void playWinMusic() {
        play("res/audio/win_music.wav");
    }

    /**
     * Plays the lose music.
     */
    public void playLoseMusic() {
        play("res/audio/lose_music.wav");
    }

    /**
     * Plays the tie music.
     */
    public void playTieMusic() {
        play("res/audio/lose_music.wav");
    }
}