package com.movieexplorer.mobileapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SavedSharedPreferences {

    private static final String PREF_NAME = "MyPreferences";
    private static final String KEY_BOOLEAN_VALUE = "isLoggedIn";

    private final SharedPreferences sharedPreferences;

    public SavedSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean getBooleanValue() {
        // The second parameter is the default value if the key is not found.
        return sharedPreferences.getBoolean(KEY_BOOLEAN_VALUE, false);
    }

    public void setBooleanValue(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_BOOLEAN_VALUE, value);
        editor.apply();
    }

}
