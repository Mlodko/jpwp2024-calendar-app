package client.backend.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class User implements Savable<User>{
    @Expose
    String username;
    String password;
    @Expose
    String passwordHash;
    @Expose
    String email;

    // TODO permissions

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordHash = sha256Hex(password);
    }

    @Override
    public User loadFromString(String json_text) {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(json_text, User.class);
    }

    @Override
    public String saveToString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
