package client.backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
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

    public User(String username, String password, String email) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
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

    @Override
    public User loadFromString(String json_text) {
        return gson.fromJson(json_text, User.class);
    }

    @Override
    public String saveToString() {
        return gson.toJson(this);
    }

    public static void main(String[] args) {
        File file = new File("workspace/papaj.json");
        String papaj = "";

        try {
            papaj = Files.readString(file.toPath());
            System.out.println(papaj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        User usr = new User().loadFromString(papaj);
        System.out.println("usernam: " + usr.username);
    }
}