package in.co.ophio.keystore_preferences.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;


import in.co.ophio.keystore_preferences.KeystoreApplication;
import in.co.ophio.keystore_preferences.entity.UserCredential;
import in.co.ophio.secure.core.KeyGenerator;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class KeystoreAccountUtils implements AccountUtils {

    private static final String PREFS_NAME = "simple_preferences";
    private static final String USER = "user_name";
    private static final String PASS = "password";
    private final SharedPreferences sharedPreferences;

    public KeystoreAccountUtils() {
        sharedPreferences = KeystoreApplication.getAppContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * //TODO use getKey to get an encrypted Key for obscuring preferences
     *
     * @return
     */
    private String getKey() {
        String sekrt = null;
        try {
            sekrt = KeyGenerator.get(KeystoreApplication.getAppContext(), KeystoreApplication.getAppContext()
                    .getPackageName()).loadOrGenerateKeys();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("can't create  key");
        } catch (IOException e) {
            throw new RuntimeException("can't create key");
        }
        return sekrt;
    }

    @Override
    public void login(UserCredential userCredential) {
        sharedPreferences.edit().putString(USER, userCredential.username).commit();
        sharedPreferences.edit().putString(PASS, userCredential.password).commit();
    }

    @Override
    public void logout() {
        sharedPreferences.edit().clear();
    }

    @Override
    public boolean isLoggedIn() {
        String user = sharedPreferences.getString(USER, "");
        return !TextUtils.isEmpty(user);
    }
}
