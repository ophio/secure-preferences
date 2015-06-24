package in.co.ophio.keystore_preferences.entity;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class UserCredential {

    public UserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username;
    public String password;
}
