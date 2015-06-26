package in.co.ophio.secure.core;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public interface KeyGenerator {

    /**
     * @return String key to be used in encryption algorithms
     * @throws GeneralSecurityException In case of Security Related exceptions
     * @throws IOException If could unable to read/write key
     */
    String loadOrGenerateKeys() throws GeneralSecurityException, IOException;

    /**
     * @return boolean to know whether key is hardware backed or not
     */
    boolean isHardwareBacked();

}
