package server;

import client.backend.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserManager {
    private ArrayList<User> userCache;

    public UserManager() {
        try {
            this.userCache = ServerJsonManager.readUsersData();
        } catch(IOException e) {
            e.printStackTrace();
        }
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
        userCache.add(user);

        try {
            ServerJsonManager.addToUsers(user);
        } catch(IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(user);
    }

    public void updateUserCache() {
        try {
            this.userCache = ServerJsonManager.readUsersData();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
