For securing your `SharedPreferences` information.

## Why Secure `SharedPreferences`?
`SharedPreferences` are accessible to anybody if your device is compromised. It is recommended to obscure the information saved in `SharedPreferences` before you store them (See [`ObscuredSharedPreferences`] (library/src/main/java/in/co/ophio/secure/core/ObscuredSharedPreferences.java)). Still the `key` that is used to obscure the information can be recovered by a simple decompilation procedure if it is hard-coded in the app. 

## Solution
In addition to obscuring the information stored in `SharedPreferences,`  store your key in the [`Android Keystore System`] (https://developer.android.com/training/articles/keystore.html#UsingAndroidKeyStore) by using our [`KeyGenerator`](library/src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java)

## Android Keystore System
The Android Keystore system lets you store private keys in a container to make it more difficult to extract from the device. Once keys are in the keystore, they can be used for cryptographic operations with the private key material remaining non-exportable.

The Keystore system is used by the [`KeyChain`](https://developer.android.com/reference/android/security/KeyChain.html) API as well as the Android Keystore provider feature that was introduced in Android 4.3 (API level 18).

#Usage

* Generate a key using [`KeyGenerator`](library/src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java):

```
	String key = KeyGenerator.get(KeystoreApplication.getAppContext(),
					KeystoreApplication.getAppContext().getPackageName())
						.loadOrGenerateKeys();
```

* Obtain an instance of [`ObscuredSharedPreferences`] (library/src/main/java/in/co/ophio/secure/core/ObscuredSharedPreferences.java) using [`ObscuredPreferencesBuilder`](library/src/main/java/in/co/ophio/secure/core/ObscuredPreferencesBuilder.java) as follows:

```
SharedPreferences sharedPreferences = new ObscuredPreferencesBuilder()
                .setApplication(KeystoreApplication.getAppContext())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName(PREFS_NAME)
                .setSecret(key)		//secret key we generated in step 1.
                .createSharedPrefs();
```

* For more information on usage see the sample application [implementation](app/src/main/java/in/co/ophio/keystore_preferences/util/KeystoreAccountUtils.java):
* For more information on library see the [library page](library/).



# License

```
Copyright 2015 Ophio.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```