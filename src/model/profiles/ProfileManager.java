package model.profiles;

import java.io.File;
import java.util.ArrayList;

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

    public void selectProfile(String username) {
        for(Profile profile : profiles) {
            if(profile.getUsername().equals(username)) {
                selectedProfile = profile;
                return;
            }
        }
    }
    public void saveSelectedProfile() {
        selectedProfile.saveProfile();
    }


}
