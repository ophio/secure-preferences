package in.co.ophio.secure.core;

import android.app.Application;
import android.test.AndroidTestCase;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


/**
 * @author Gaurav Vashisth (vashisthg@gmail.com).
 */
public class KeyGeneratorTests extends AndroidTestCase {


    public void testKeyGenerator() throws GeneralSecurityException, IOException {
        String keyFirst = KeyGenerator.get((Application) getContext().getApplicationContext(), "testname")
                .loadOrGenerateKeys();

        String keySecond = KeyGenerator.get((Application) getContext().getApplicationContext(), "testname")
                .loadOrGenerateKeys();

        // check if same key is returned every time
        assertThat(keySecond, equalTo(keyFirst));
    }

    public void testKeyIsDifferntForDifferentFile() throws GeneralSecurityException, IOException {
        String keyFirst = KeyGenerator.get((Application) getContext().getApplicationContext(), "testnamea")
                .loadOrGenerateKeys();

        String keySecond = KeyGenerator.get((Application) getContext().getApplicationContext(), "testname")
                .loadOrGenerateKeys();

        // check if same key is returned every time
        assertThat(keySecond, not(equalTo(keyFirst)));
    }
}
