package in.co.ophio.secure.sample;

import android.app.Application;

import in.co.ophio.secure.sample.module.AppComponent;
import in.co.ophio.secure.sample.module.AppModule;
import in.co.ophio.secure.sample.module.DaggerAppComponent;

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
