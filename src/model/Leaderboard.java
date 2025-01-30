package model;

import model.profiles.Profile;
import model.profiles.ProfileManager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leaderboard {
    private static Leaderboard instance = null;
    private List<Profile> profiles;

    private Leaderboard() {
        ProfileManager profileManager = ProfileManager.getInstance();
        profiles = IntStream.range(0, profileManager.getProfilesSize())
                .mapToObj(profileManager::getProfile)
                .collect(Collectors.toList());
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public List<Profile> getTopProfiles(int limit) {
        return profiles.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
