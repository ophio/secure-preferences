package in.co.ophio.keystore_preferences.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.co.ophio.keystore_preferences.util.AccountUtils;
import in.co.ophio.keystore_preferences.util.KeystoreAccountUtils;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton Context provideContext() {
        return this.context;
    }

    @Provides @Singleton AccountUtils provideAccountUtils() {
        return new KeystoreAccountUtils();
    }

}
