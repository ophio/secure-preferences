package in.co.ophio.secure.sample.util;

import in.co.ophio.secure.sample.entity.UserCredential;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public interface AccountUtils {

    void login(UserCredential credential);

    void logout();

    boolean isLoggedIn();

}
