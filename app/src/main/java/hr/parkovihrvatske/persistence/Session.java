package hr.parkovihrvatske.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import hr.parkovihrvatske.model.User;

/**
 * Created by Damjan Miloševski on 05.6.2018.
 * Project: ParkoviHrvatske
 * Copyright: Corvus Info d.o.o.
 * Buzinski prilaz 10, 10010 Zagreb, HR
 */
public class Session {
    private static final String PREFERENCES_NAME = "ParkoviHrvatskePrefs";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    private static final String SLIDESHOW = "Slideshow";
    private static final String TIMEOUT = "Timeout";
    private static final String USER = "User";
    private Context context;
    private SharedPreferences preferences;

    public Session(Context context) {
        int PRIVATE_MODE = 0;
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, PRIVATE_MODE);
    }

    public void setLogin(boolean isLoggedIn, User user) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String userToJson = gson.toJson(user);
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.putString(USER, userToJson);
        editor.apply();
    }

    public void clearSession() {
        SharedPreferences.Editor editor = preferences.edit()
                .remove(IS_LOGGED_IN)
                .remove(USER);
        editor.apply();
    }
    public void setSlideshow(boolean status){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SLIDESHOW,status);
        editor.apply();
    }
    public boolean isSlideShowEnabled(){
        return preferences.getBoolean(SLIDESHOW,true);
    }
    public void setTimeout(long timeout){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(TIMEOUT,timeout);
        editor.apply();
    }
    public long getTimeout(){
        return preferences.getLong(TIMEOUT,10000);
    }
    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }
}
