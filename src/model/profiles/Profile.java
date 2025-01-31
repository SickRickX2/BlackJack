package model.profiles;

import java.io.*;

/**
 * The Profile class represents a user profile in the game.
 * It implements Serializable to allow saving and loading profiles.
 */
public class Profile implements Serializable {
    private String username;
    private int avatarID;
    private int numberOfWins;
    private int numberOfPlayedGames;

    /**
     * Constructs a Profile with the specified avatar ID and username.
     * Initializes the number of wins and played games to zero.
     *
     * @param avatarID the avatar ID of the profile
     * @param username the username of the profile
     */
    public Profile(int avatarID, String username) {
        this.username = username;
        this.avatarID = avatarID;
        this.numberOfWins = 0;
        this.numberOfPlayedGames = 0;
    }

    /**
     * Saves the profile to a file.
     * The file is named based on the username and stored in the 'res/profiles' directory.
     */
    public void saveProfile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("res/profiles/" + username + ".rl");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(username);
            objectOut.writeObject(avatarID);
            objectOut.writeObject(numberOfWins);
            objectOut.writeObject(numberOfPlayedGames);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a profile from a file.
     * The file is specified by the filename parameter.
     *
     * @param filename the name of the file to load the profile from
     * @return the loaded Profile object, or null if an error occurs
     */
    public static Profile loadProfile(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            String username = (String) objectIn.readObject();
            int avatarID = (int) objectIn.readObject();
            int numberOfWins = (int) objectIn.readObject();
            int numberOfPlayedGames = (int) objectIn.readObject();
            Profile profile = new Profile(avatarID, username);
            profile.numberOfWins = numberOfWins;
            profile.numberOfPlayedGames = numberOfPlayedGames;
            objectIn.close();
            fileIn.close();
            return profile;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the username of the profile.
     *
     * @return the username of the profile
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the avatar ID of the profile.
     *
     * @return the avatar ID of the profile
     */
    public int getAvatarID() {
        return avatarID;
    }

    /**
     * Returns the number of wins of the profile.
     *
     * @return the number of wins of the profile
     */
    public int getWins() {
        return numberOfWins;
    }

    /**
     * Increases the number of wins by one.
     */
    public void increaseNumberOfWins() {
        numberOfWins++;
    }

    /**
     * Returns the number of played games of the profile.
     *
     * @return the number of played games of the profile
     */
    public int getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    /**
     * Increases the number of played games by one.
     */
    public void increaseNumberOfPlayedGames() {
        numberOfPlayedGames++;
    }
}