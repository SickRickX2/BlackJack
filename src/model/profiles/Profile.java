package model.profiles;

import java.io.*;

public class Profile implements Serializable {
    private String username;
    private int avatarID;
    private int numberOfWins;
    private int numberOfPlayedGames;
    public Profile(int avatarID, String username) {
        this.username = username;
        this.avatarID = avatarID;
        this.numberOfWins = 0;  // Default value
        this.numberOfPlayedGames = 0;  // Default value

    }

    public void saveProfile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("res/profiles/" + username+ ".rl");
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




    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAvatarID() {
        return avatarID;
    }
    public void setAvatarID(int avatarID) {
        this.avatarID = avatarID;
    }
    public int getWins() {
        return numberOfWins;
    }
    public void increaseNumberOfWins() {
       numberOfWins++;
    }
    public int getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }
    public void increaseNumberOfPlayedGames() {
        numberOfPlayedGames++;
    }
}
