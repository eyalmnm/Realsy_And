package io.ideaction.raelsy.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesUtils {
    private static final String TAG = "PreferencesUtils";

    // Shared preferences file name
    private static final String PREF_NAME = "realsy";
    private static PreferencesUtils instance = null;

    // Shared preferences access components
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    // Application context
    private Context context;

    // Shared preferences working mode
    private int PRIVATE_MODE = 0;

    private PreferencesUtils(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public static PreferencesUtils getInstance(Context context) {
        if (null == instance) {
            instance = new PreferencesUtils(context);
        }
        return instance;
    }


    // Intro Screen Preferences
    public void setIntroView() {
        editor.putBoolean("introCompleted", true);
        editor.commit();
    }

    public boolean getIntroView() {
        return preferences.getBoolean("introCompleted", false);
    }

    // Facebook Preferences
    public String getFbToken() {
        return preferences.getString("fbToken", null);
    }

    public void setFbToken(String token) {
        editor.putString("fbToken", token);
        editor.commit();
    }

    // Client ID Preferences
    public String getMyClientId() {return preferences.getString("clientId", null); }

    public void setMyClientId(String clientId) {
        editor.putString("clientId", clientId);
        editor.commit();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        editor = null;
        preferences = null;
    }
}
