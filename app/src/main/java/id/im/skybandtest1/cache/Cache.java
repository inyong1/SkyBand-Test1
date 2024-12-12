package id.im.skybandtest1.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import id.im.skybandtest1.SkybandECRApplication;

import java.util.Map;
import java.util.Set;

public class Cache {

    public static final String SP_NAME_GENERAL_PARAM = "generalParam";

    protected SharedPreferences sp;
    protected SharedPreferences.Editor editor;

    public Cache(String spName) {
        Context context = SkybandECRApplication.Instance().getApplicationContext();
        if (TextUtils.isEmpty(spName)) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
        editor = sp.edit();
        editor.apply();
    }

    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, -1L);
    }

    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return getFloat(key, -1f);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public void remove(String key) {
        editor.remove(key).apply();
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public void clear() {
        editor.clear().apply();
    }

    public void putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        return sp.getStringSet(key, defValues);
    }
}
