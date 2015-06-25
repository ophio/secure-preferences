package in.co.ophio.secure.sample.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.co.ophio.secure.sample.util.AccountUtils;
import in.co.ophio.secure.sample.util.KeystoreAccountUtils;
import in.co.ophio.secure.core.KeyGenerator;
import in.co.ophio.secure.core.KeyStoreKeyGenerator;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Context provideContext() {
        return this.application;
    }

    @Provides @Singleton AccountUtils provideAccountUtils() {
        return new KeystoreAccountUtils();
    }

    @Provides @Singleton KeyGenerator provideKeyGenerator() {
        return KeyStoreKeyGenerator.get(application, application.getPackageName());
    }

}
