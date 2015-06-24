package in.co.ophio.keystore_preferences.util;

import in.co.ophio.keystore_preferences.entity.UserCredential;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public interface AccountUtils {

    void login(UserCredential credential);

    void logout();

    boolean isLoggedIn();

}
