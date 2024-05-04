package server;

import client.backend.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class UserManager {
    // hold ALL USERS since the beginning
    private static ArrayList<User> userCache;
    private static final ArrayList<User> loggedInUsers = new ArrayList<>();

    public UserManager() {
        if(userCache == null || userCache.isEmpty()) {
            updateUserCache();
        }
    }
    
    public Optional<User> loginUser(String username, String password) {
        Optional<User> loggedInUser = authenticateUser(username, password);

        if(loggedInUser.isPresent()) {
            loggedInUser.get().setAuthToken(UUID.randomUUID().toString());
            loggedInUsers.add(loggedInUser.get());
        }

        return loggedInUser;
    }

    public Optional<User> authenticateUser(String username, String passwordHash) {
        Optional<User> checkedUser = userCache.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if(checkedUser.isPresent() &&
            checkedUser.get().getPasswordHash().equals(passwordHash)) {
            return checkedUser;
        }

        return Optional.empty();
    }

    public Optional<User> registerUser(String username, String passwordHash, String email) {
        if(userCache.stream()
                .anyMatch(user -> user.getUsername().equals(username))) {
            // User with same username found, can't register
            return Optional.empty();
        }

        User user = new User(username, passwordHash, email);
        user.setAuthToken(UUID.randomUUID().toString());

        if(!ServerJsonManager.addToUsers(user)) {
            // Saving to users.json failed
            return Optional.empty();
        }

        loggedInUsers.add(user);
        userCache.add(user);

        return Optional.of(user);
    }

    public void updateUserCache() {
        try {
            userCache = ServerJsonManager.readUsersData();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean ifAuthTokenInLoggedInUsers(String authToken) {
        return loggedInUsers.stream().map(User::getAuthToken).toList().contains(authToken);
    }

    public static Optional<User> findUserWithAuthToken(String authToken) {
        return loggedInUsers.stream().filter(user -> user.getAuthToken().equals(authToken)).findFirst();
    }
}
