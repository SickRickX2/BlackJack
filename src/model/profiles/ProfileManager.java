package model.profiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProfileManager class manages user profiles in the game.
 * It follows the singleton pattern to ensure only one instance exists.
 */
public class ProfileManager {
    private static ProfileManager instance = null;
    private ArrayList<Profile> profiles;
    private Profile selectedProfile;

    /**
     * Private constructor to prevent instantiation.
     * Loads profiles from the 'res/profiles' directory.
     * If no profiles are found, it creates default profiles.
     */
    private ProfileManager() {
        profiles = new ArrayList<>();
        for (File file : new File("res/profiles/").listFiles()) {
            if (file.isFile()) {
                profiles.add(Profile.loadProfile(file.getPath()));
            }
        }
        if (profiles.size() == 0) {
            createProfile("Rick", 0);
            createProfile("Asuka", 1);
            createProfile("Shinji", 2);
            createProfile("Rei", 3);
            createProfile("Mari", 4);
            createProfile("Eva-01", 5);
        }
    }

    /**
     * Returns the singleton instance of the ProfileManager.
     *
     * @return the singleton instance of the ProfileManager
     */
    public static ProfileManager getInstance() {
        if (instance == null) {
            instance = new ProfileManager();
        }
        return instance;
    }

    /**
     * Creates a new profile with the specified username and avatar ID.
     * Saves the profile and adds it to the list of profiles.
     *
     * @param username the username of the new profile
     * @param avatarID the avatar ID of the new profile
     */
    public void createProfile(String username, int avatarID) {
        Profile profile = new Profile(avatarID, username);
        profile.saveProfile();
        profiles.add(profile);
        selectedProfile = profile;
    }

    /**
     * Selects a profile based on the specified index.
     *
     * @param index the index of the profile to select
     */
    public void selectProfile(int index) {
        selectedProfile = profiles.get(index);
    }

    /**
     * Returns the number of profiles.
     *
     * @return the number of profiles
     */
    public int getProfilesSize() {
        return profiles.size();
    }

    /**
     * Returns the profile at the specified index.
     *
     * @param index the index of the profile to return
     * @return the profile at the specified index
     */
    public Profile getProfile(int index) {
        return profiles.get(index);
    }

    /**
     * Saves the currently selected profile.
     */
    public void saveSelectedProfile() {
        selectedProfile.saveProfile();
    }

    /**
     * Increases the win counter of the selected profile and saves it.
     */
    public void winCounter() {
        selectedProfile.increaseNumberOfWins();
        saveSelectedProfile();
    }

    /**
     * Increases the game counter of the selected profile and saves it.
     */
    public void gameCounter() {
        selectedProfile.increaseNumberOfPlayedGames();
        saveSelectedProfile();
    }

    /**
     * Returns the currently selected profile.
     *
     * @return the currently selected profile
     */
    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    /**
     * Returns the list of profiles.
     *
     * @return the list of profiles
     */
    public List<Profile> getProfiles() {
        return profiles;
    }
}