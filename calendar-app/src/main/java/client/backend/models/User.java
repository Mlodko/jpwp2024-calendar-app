package client.backend.models;

import client.backend.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.net.ConnectException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class User implements Savable<User>{

    @Expose String id;
    @Expose String username;
    @Expose String passwordHash;
    @Expose String email;
    String password;


    ArrayList<Calendar> userCalendars = new ArrayList<>();

    static Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    // TODO permissions

    protected User() { }

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
    //endregion

    //region Logging in/registering

    public static Optional<User> login(String username, String password) throws Exception {
        Optional<User> loggedInUser;

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

    @Override
    public User loadFromString(String json_text) {
        return gson.fromJson(json_text, User.class);
    }

    @Override
    public String saveToString() {
        return gson.toJson(this);
    }

    public static void main(String[] args) throws Exception {
        Optional<User> user = User.register("1", "2", "3");
    }
}
