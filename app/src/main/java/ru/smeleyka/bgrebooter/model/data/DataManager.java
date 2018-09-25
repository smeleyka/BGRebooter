package ru.smeleyka.bgrebooter.model.data;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

@Singleton
public class DataManager {
    private SharedPrefsHelper sharedPrefsHelper;

    @Inject
    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAuthKey(String authKey){
        sharedPrefsHelper.put(sharedPrefsHelper.PREF_AUTH_KEY,authKey);
    }

    public String getAuthKey(){
        return sharedPrefsHelper.get(sharedPrefsHelper.PREF_AUTH_KEY,null);
    }

    public void deleteAuthKey(){
        sharedPrefsHelper.deleteSavedData(sharedPrefsHelper.PREF_AUTH_KEY);
    }

    public void saveLogin(String authKey){
        sharedPrefsHelper.put(sharedPrefsHelper.PREF_LOGIN,authKey);
    }

    public String getLogin(){
        return sharedPrefsHelper.get(sharedPrefsHelper.PREF_LOGIN,null);
    }

    public void deleteLogin(){
        sharedPrefsHelper.deleteSavedData(sharedPrefsHelper.PREF_LOGIN);
    }

}
