package in.co.ophio.secure.core;

import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * @author Gaurav Vashisth (vashisthg@gmail.com).
 */
public class ObscuredSharedPreferencesTest extends AndroidTestCase {

    public static final String KEY_BOOLEAN = "KEY_BOOLEAN";
    public static final String KEY_FLOAT = "KEY_FLOAT";
    public static final String KEY_INT = "KEY_INT";
    public static final String KEY_LONG = "KEY_LONG";
    public static final String KEY_STRINGSET = "KEY_STRINGSET";
    public static final String KEY_STRING = "KEY_STRING";
    private static final String KEY_GETALL_INT = "KEY_GETALL_INT";
    private static final String KEY_GETALL_STRING = "KEY_GETALL_STRING";
    private static final String KEY_GETALL_LONG = "KEY_GETALL_LONG";

    private SharedPreferences sharedPreferences;
    private ObscuredSharedPreferences obscuredSharedPreferences;

    @Override
    public void setUp() {
        sharedPreferences = getContext().getSharedPreferences("PREF", 1);
        obscuredSharedPreferences = new ObscuredSharedPreferences(getContext(), sharedPreferences);
        obscuredSharedPreferences.setSecret("SOME_SECRET_KEY");
        assertNotNull(obscuredSharedPreferences);
    }

    @Override
    protected void tearDown() throws Exception {
        obscuredSharedPreferences.edit().clear();
    }

    public void testPutString() {
        obscuredSharedPreferences.edit().putString(KEY_STRING, "STRING123").commit();
        assertThat(obscuredSharedPreferences.getString(KEY_STRING, "null"), equalTo("STRING123"));
    }

    public void testPutBoolean() throws Exception {
        obscuredSharedPreferences.edit().putBoolean(KEY_BOOLEAN, true).commit();
        assertThat(obscuredSharedPreferences.getBoolean(KEY_BOOLEAN, false), is(true));
    }

    public void testPutFloat() throws Exception {
        obscuredSharedPreferences.edit().putFloat(KEY_FLOAT, 12.03f).commit();
        assertThat(obscuredSharedPreferences.getFloat(KEY_FLOAT, 0.00f), equalTo(12.03f));
    }

    public void testPutInt() throws Exception {
        obscuredSharedPreferences.edit().putInt(KEY_INT, 123).commit();
        assertThat(obscuredSharedPreferences.getInt(KEY_INT, 0), equalTo(123));
    }

    public void testPutLong() throws Exception {
        obscuredSharedPreferences.edit().putLong(KEY_LONG, 12343422l).commit();
        assertThat(obscuredSharedPreferences.getLong(KEY_LONG, 0l), equalTo(12343422l));
    }

    public void testGetAll() {
        obscuredSharedPreferences.edit().putInt(KEY_GETALL_INT, 1).commit();
        obscuredSharedPreferences.edit().putString(KEY_GETALL_STRING, "One").commit();
        obscuredSharedPreferences.edit().putLong(KEY_GETALL_LONG, 1234L).commit();
        Map<String, String> all = obscuredSharedPreferences.getAll();
        assertEquals(all.size(), 3);
        assertEquals(all.get(KEY_GETALL_INT), "1");
        assertEquals(all.get(KEY_GETALL_STRING), "One");
        assertEquals(all.get(KEY_GETALL_LONG), "1234");
    }

    public void testPutStringSet() throws Exception {
        Set<String> inputStringSet = new HashSet<>();
        inputStringSet.add("one");
        inputStringSet.add("two");
        obscuredSharedPreferences.edit().putStringSet(KEY_STRINGSET, inputStringSet).commit();

        Set<String> outputStringSet = obscuredSharedPreferences.getStringSet(KEY_STRINGSET, null);
        assertNotNull(outputStringSet);
        assertThat(outputStringSet.size(), is(2));
        assertThat(outputStringSet.contains("one"), is(true));
        assertThat(outputStringSet.contains("two"), is(true));
    }

    public void testRemove() throws Exception {
        obscuredSharedPreferences.edit().putString(KEY_STRING, "test").commit();
        assertThat(obscuredSharedPreferences.contains(KEY_STRING), is(true));

        obscuredSharedPreferences.edit().remove(KEY_STRING);
        assertThat(obscuredSharedPreferences.contains(KEY_STRING), is(true));
    }
}
