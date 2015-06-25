package in.co.ophio.keystore_preferences;

import android.app.Application;

import in.co.ophio.keystore_preferences.module.AppComponent;
import in.co.ophio.keystore_preferences.module.AppModule;
import in.co.ophio.keystore_preferences.module.DaggerAppComponent;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class KeystoreApplication extends Application {

    private AppComponent appComponent;
    private static KeystoreApplication appContext;

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    public static KeystoreApplication getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = (KeystoreApplication) getApplicationContext();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

    }
}
