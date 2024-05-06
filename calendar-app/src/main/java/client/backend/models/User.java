package client.backend.models;

import client.backend.JsonManager;
import client.backend.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.net.ConnectException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class User implements Savable<User>{

    @Expose String id = new String();
    @Expose String username = new String();
    @Expose String passwordHash = new String();
    @Expose String email = new String();
    @Expose ArrayList<String> assignedWorkspaceIds = new ArrayList<>();
    String password;
    String authToken; // Token that the server sends on login, used to authenticate the user from now on

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();


    public User() { }

    public User(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordHash = sha256Hex(password);
    }

    public User(String username, String passwordHash, String email) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.passwordHash = sha256Hex(password);
    }

    //region Getters/setters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        this.passwordHash = sha256Hex(password);
        return this;
    }

    public String getAuthToken() {
        return authToken;
    }

    public User setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public ArrayList<String> getAssignedWorkspaceIds() {
        if(assignedWorkspaceIds == null) {
            assignedWorkspaceIds = new ArrayList<>();
        }
        return this.assignedWorkspaceIds;
    }

    public User setAssignedWorkspaceIds(ArrayList<String> workspaceIds) {
        this.assignedWorkspaceIds = workspaceIds;
        return this;
    }

    public void addToAssignedWorkspaceIds(String... workspaceIds) {
        if(assignedWorkspaceIds == null) {
            assignedWorkspaceIds = new ArrayList<>();
        }
        assignedWorkspaceIds.addAll(List.of(workspaceIds));
    }

    //endregion

    //region Logging in/registering

    public static Optional<User> login(String username, String password) throws Exception {
        Optional<User> loggedInUser;

        // TODO does it download all data to local?
        // TODO read workspace ids here
        try(RequestManager manager = new RequestManager()) {
            User temp = new User(username, password);
            loggedInUser = manager.makeLoginRequest(temp);
        }

        return loggedInUser.map(user -> user.setPassword(password));
    }

    public static Optional<User> register(String username, String password, String email) throws Exception {
        Optional<User> registeredUser;

        try(RequestManager manager = new RequestManager()) {
            User temp = new User(username, sha256Hex(password), email);
            registeredUser = manager.makeRegisterRequest(temp);
        }

        return registeredUser.map(user -> user.setPassword(password));
    }

    //endregion


    public boolean logOut(Workspace workspace) throws Exception {

        JsonManager.writeALLdata(workspace);

        return this.logOut();
    }
    public boolean logOut() throws Exception {

        try (RequestManager manager = new RequestManager()) {
            if (!manager.makeLogoutRequest(this)) {
                throw new Exception("Logout request failed");
            }
        }

        this.id = "";
        this.username = "";
        this.passwordHash = "";
        this.email = "";
        this.assignedWorkspaceIds = new ArrayList<>();
        this.password = "";
        this.authToken = "";
        JsonManager.removeAllLocalData();

        return true;
    }


    @Override
    public User loadFromString(String json_text) {
        return gson.fromJson(json_text, User.class);
    }

    @Override
    public String saveToString() {
        return gson.toJson(this);
    }

    /*
    public static void main(String[] args) throws Exception {
        Optional<User> user = User.register("1", "2", "3");
    }*/
}
