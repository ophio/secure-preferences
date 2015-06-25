package in.co.ophio.secure.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author Gaurav Vashisth (vashisthg@gmail.com)
 */
public class ObscuredPreferencesBuilder {

    public static final String DEFAULT_PREF_NAME = "shared_pref";
    private Application application;
    //    private SharedPreferences sharedPreference;
    private String sharedPrefFileName = DEFAULT_PREF_NAME;
    private boolean isObfuscated;
    private boolean isKeyObfuscated = true;
    private String secret;

    public ObscuredPreferencesBuilder setApplication(Application application) {
        this.application = application;
        return this;
    }

    public ObscuredPreferencesBuilder setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public ObscuredPreferencesBuilder obfuscateValue(boolean obfuscated) {
        this.isObfuscated = obfuscated;
        return this;
    }

    public ObscuredPreferencesBuilder obfuscateKey(boolean obfuscateKey) {
        this.isKeyObfuscated = obfuscateKey;
        return this;
    }

    public ObscuredPreferencesBuilder setSharePrefFileName(String fileName) {
        this.sharedPrefFileName = fileName;
        return this;
    }

    public SharedPreferences createSharedPrefs() {

        SharedPreferences sharedPrefDelegate = application.getSharedPreferences(sharedPrefFileName, Context
                .MODE_PRIVATE);
        SharedPreferences sharedPreferences;
        if (isObfuscated || isKeyObfuscated) {
            sharedPreferences = new ObscuredSharedPreferences(
                    application,
                    sharedPrefDelegate, isKeyObfuscated);
            if (!TextUtils.isEmpty(this.secret)) {
                ((ObscuredSharedPreferences) sharedPreferences).setSecret(secret);
            } else {
                Log.d("SharedPrefsBuilder", "secret is empty");
            }
        } else {
            sharedPreferences = sharedPrefDelegate;
        }

        return sharedPreferences;
    }
}
