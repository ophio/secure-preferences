package in.co.ophio.secure.sample.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.security.GeneralSecurityException;


import javax.inject.Inject;

import in.co.ophio.secure.sample.KeystoreApplication;
import in.co.ophio.secure.sample.entity.UserCredential;
import in.co.ophio.secure.core.KeyGenerator;
import in.co.ophio.secure.core.ObscuredPreferencesBuilder;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class KeystoreAccountUtils implements AccountUtils {

    private static final String PREFS_NAME = "shared_preferences_name";
    private static final String USER = "user_name";
    private static final String PASS = "password";
    private final SharedPreferences sharedPreferences;

    @Inject KeyGenerator keyGenerator;
    @Inject Context context;

    public KeystoreAccountUtils() {
        KeystoreApplication.getAppContext().getAppComponent().inject(this);
        String sekrt = getKey();
        sharedPreferences = new ObscuredPreferencesBuilder()
                .setApplication(KeystoreApplication.getAppContext())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(PREFS_NAME)
                .setSecret(sekrt)
                .createSharedPrefs();
    }

    /**
     * use getKey to get an encrypted Key for obscuring preferences
     *
     * @return
     */
    private String getKey() {
        String sekrt = null;
        try {
            sekrt = keyGenerator.loadOrGenerateKeys();
        } catch (GeneralSecurityException e) {
            Toast.makeText(context, "can't create key", Toast.LENGTH_SHORT).show();
            throw new RuntimeException("can't create  key");
        } catch (IOException e) {
            Toast.makeText(context, "can't create key", Toast.LENGTH_SHORT).show();
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
        sharedPreferences.edit().clear().commit();
    }

    @Override
    public boolean isLoggedIn() {
        String user = sharedPreferences.getString(USER, "");
        return !TextUtils.isEmpty(user);
    }
}
