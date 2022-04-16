package com.ahmdalii.medicinereminder.db.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static SharedPrefManager sharedPrefManager;

    private SharedPrefManager(Context context, String fileName) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static synchronized SharedPrefManager getInstance(Context context, String fileName) {
        if (sharedPrefManager == null) {
            sharedPrefManager = new SharedPrefManager(context, fileName);
        }
        return sharedPrefManager;
    }

    public void setValue(String key, Object value){
        if (value instanceof Integer){
            editor.putInt(key, (int) value);
        } else if (value instanceof String){
            editor.putString(key, (String) value);
        } else if (value instanceof Float){
            editor.putFloat(key, (float) value);
        } else if (value instanceof Long){
            editor.putLong(key, (long) value);
        } else if (value instanceof Boolean){
            editor.putBoolean(key, (boolean) value);
        }
        editor.apply();
    }

    public int getIntValue(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public long getLongValue(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public boolean getBooleanValue(String keyFlag) {
        return sharedPreferences.getBoolean(keyFlag, false);
    }

    public void removeKey(String key) {
        editor.remove(key);
        editor.apply();
    }

    public void clear() {
        editor.clear().apply();
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, "null");
    }
}
