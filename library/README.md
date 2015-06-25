This library contains the following:

* [`ObscuredSharedPreferences`] (src/main/java/in/co/ophio/secure/core/ObscuredSharedPreferences.java): An implementation of `SharedPreferences` that can store keys and values in obscured manner.
* [`ObscuredPreferencesBuilder`] (src/main/java/in/co/ophio/secure/core/ObscuredPreferencesBuilder.java): Can be used to obtain an instance of `ObscuredSharedPreferences`.
* [`KeyStoreKeyGenerator`] (src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java): used to generate keys using `Android Keystore System`


## [`ObscuredSharedPreferences`] (src/main/java/in/co/ophio/secure/core/ObscuredSharedPreferences.java)

* Main idea by Mike Burton in an answer posted [here](http://stackoverflow.com/questions/785973/what-is-the-most-appropriate-way-to-store-user-settings-in-android-application). 
* It is mandatory to set key using `setSecret()` method otherwise `IllegalAccessException` will be thrown. 
* Uses `PBEWithMD5AndDES` to encode the keys and/or values.
* Overrides methods like `putBoolean(), putFloat(), getBoolean(), getFloat(), contains(),` ` getAll(),` ... to provide an obscured implementation.

## [`ObscuredPreferencesBuilder`] (library/src/main/java/in/co/ophio/secure/core/ObscuredPreferencesBuilder.java)

* Will return an instance of `ObscuredPreferencesBuilder` if atleast one of the `obfuscateKey()` OR `obfuscateValue()` is set.
* If none of the above methods are set it will return simple `SharedPreferences` implementation.
* Example:

```
SharedPreferences sharedPreferences = new ObscuredPreferencesBuilder()
                .setApplication(KeystoreApplication.getAppContext())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(PREFS_NAME)
                .setSecret("some key")		
                .createSharedPrefs();
```

## [`KeyStoreKeyGenerator`] (src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java)
* method `loadOrGenerateKeys()`:  Subsequent calls of same method in an application returns the same key. Uses `Android Keystore System` to generate and return key. It throws `IllegalStateException` if some error occurred while trying to generate keys.
* method `isHardwareBacked()`:  Flag indicating that the `SecretKeyWrapper` public/private key is hardware-backed. A software keystore is more vulnerable to offline attacks if the device is compromised.
* Example:

```
	String key = KeyGenerator.get(KeystoreApplication.getAppContext(),
					KeystoreApplication.getAppContext().getPackageName())
						.loadOrGenerateKeys();
```
