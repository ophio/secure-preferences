[![Build Status](https://travis-ci.org/ophio/secure-preferences.svg?branch=develop)](https://travis-ci.org/ophio/secure-preferences)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-secure--preferences-green.svg)](https://android-arsenal.com/details/1/2051)

For securing your `SharedPreferences` information. 

## Why Secure `SharedPreferences`?
`SharedPreferences` are accessible to anybody if your device is compromised. It is recommended to obscure the information saved in `SharedPreferences` before you store them. One of the solutions is to encrypt the informaiton (See [`ObscuredSharedPreferences`] (library/src/main/java/in/co/ophio/secure/core/ObscuredSharedPreferences.java)). Still the `key` that is used for encryption can be recovered by a simple decompilation procedure if it is hard-coded in the app. 

## Solution
In addition to encrypting the information stored in `SharedPreferences,`  store your encryption key in the [`Android Keystore System`] (https://developer.android.com/training/articles/keystore.html#UsingAndroidKeyStore) by using our [`KeyGenerator`](library/src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java)

## Android Keystore System
The Android Keystore system lets you store private keys in a container to make it more difficult to extract from the device. Once keys are in the keystore, they can be used for cryptographic operations with the private key material remaining non-exportable.

The Keystore system is used by the [`KeyChain`](https://developer.android.com/reference/android/security/KeyChain.html) API as well as the Android Keystore provider feature that was introduced in Android 4.3 (API level 18).

### Disadvantages of using Android Keystore System
If we create a key pair with the `KeyPairGenerator` and store it, then on changing the screen lock, our key pairs are deleted as the new Keystore is empty. See the following links for more details:

- [Issue](https://code.google.com/p/android/issues/detail?id=61989)
- [Explanation](http://doridori.github.io/android-security-the%20forgetful-keystore/#sthash.1opaRvjY.dpbs)

There are some changes to the Keystore in the new Android M Developer Preview regarding this mentioned [here](https://developer.android.com/preview/behavior-changes.html#behavior-keystore).

# Download
Using Gradle:

```
compile "in.co.ophio:secure-preferences:0.1.3"
```

Using Maven:

```
<dependency>
  <groupId>in.co.ophio</groupId>
  <artifactId>secure-preferences</artifactId>
  <version>0.1.3</version>
</dependency>
```

# Usage

* Generate a key using [`KeyGenerator`](library/src/main/java/in/co/ophio/secure/core/KeyStoreKeyGenerator.java):

```
	String key = KeyStoreKeyGenerator.get(getApplication(),
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

* For more information on usage see the sample application [implementation](sample/src/main/java/in/co/ophio/secure/sample/util/KeystoreAccountUtils.java):
* For more information on library see the [library page](library/).

# Contributing

Before sending pull requests

* Setup your `Preferences -> File And Code Templates -> Includes -> ` `File Header` as follows:

```
/**
 * @author ${USER} (your email address)
 */
```
*  Run the following command on your computer.

```
./gradlew clean check connectedAndroidTest
```

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
