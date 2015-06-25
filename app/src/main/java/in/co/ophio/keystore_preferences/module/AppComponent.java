package in.co.ophio.keystore_preferences.module;

import javax.inject.Singleton;

import dagger.Component;
import in.co.ophio.keystore_preferences.util.KeystoreAccountUtils;
import in.co.ophio.keystore_preferences.view.LoggedInFragment;
import in.co.ophio.keystore_preferences.view.MainActivity;
import in.co.ophio.keystore_preferences.view.MainFragment;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainFragment fragment);

    void inject(MainActivity activity);

    void inject(LoggedInFragment loggedInFragment);

    void inject(KeystoreAccountUtils keystoreAccountUtils);
}
