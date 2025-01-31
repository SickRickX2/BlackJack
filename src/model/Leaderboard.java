package model;

import model.profiles.Profile;
import model.profiles.ProfileManager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Leaderboard class represents the leaderboard in the game.
 * It provides methods to retrieve the top profiles based on their wins.
 */
public class Leaderboard {
    private static Leaderboard instance = null;
    private List<Profile> profiles;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the profiles list with profiles from the ProfileManager.
     */
    private Leaderboard() {
        ProfileManager profileManager = ProfileManager.getInstance();
        profiles = IntStream.range(0, profileManager.getProfilesSize())
                .mapToObj(profileManager::getProfile)
                .collect(Collectors.toList());
    }

    /**
     * Returns the singleton instance of the Leaderboard.
     *
     * @return the singleton instance of the Leaderboard
     */
    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    /**
     * Returns a list of the top profiles based on their wins.
     * The list is sorted in descending order of wins and limited to the specified number of profiles.
     *
     * @param limit the maximum number of top profiles to return
     * @return a list of the top profiles based on their wins
     */
    public List<Profile> getTopProfiles(int limit) {
        return profiles.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}