package in.co.ophio.secure.sample.module;

import javax.inject.Singleton;

import dagger.Component;
import in.co.ophio.secure.sample.util.KeystoreAccountUtils;
import in.co.ophio.secure.sample.view.LoggedInFragment;
import in.co.ophio.secure.sample.view.MainActivity;
import in.co.ophio.secure.sample.view.MainFragment;

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
