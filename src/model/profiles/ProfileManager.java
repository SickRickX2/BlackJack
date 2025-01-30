package model.profiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileManager {
    private static ProfileManager instance = null;
    private ArrayList<Profile> profiles;
    private Profile selectedProfile;
    public ProfileManager() {

        profiles = new ArrayList<>();
        for(File file : new File("res/profiles/").listFiles()) {
            if(file.isFile()) {
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
    public static ProfileManager getInstance() {
        if(instance == null) {
            instance = new ProfileManager();
        }
        return instance;
    }

    public void createProfile(String username, int avatarID) {
        Profile profile = new Profile(avatarID, username);
        profile.saveProfile();
        profiles.add(profile);
        selectedProfile = profile;
    }

    public void selectProfile(int index) {
        selectedProfile = profiles.get(index);
    }

    public int getProfilesSize() {
        return profiles.size();
    }
    public Profile getProfile(int index) {
        return profiles.get(index);
    }
    public void saveSelectedProfile() {
        selectedProfile.saveProfile();
    }
    public void winCounter(){
        selectedProfile.increaseNumberOfPlayedGames();
        selectedProfile.increaseNumberOfWins();
        saveSelectedProfile();

    }
    public void gameCounter(){
        selectedProfile.increaseNumberOfPlayedGames();
        saveSelectedProfile();

    }


    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
