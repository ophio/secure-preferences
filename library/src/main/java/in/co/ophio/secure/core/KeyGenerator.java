package in.co.ophio.secure.core;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public interface KeyGenerator {

    String loadOrGenerateKeys() throws GeneralSecurityException, IOException;

    /**
     * Flag indicating that the {@link com.example.android.vault.SecretKeyWrapper} public/private key is
     * hardware-backed. A software keystore is more vulnerable to offline
     * attacks if the device is compromised.
     */
    boolean isHardwareBacked();

}
