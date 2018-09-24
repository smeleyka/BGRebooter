package ru.smeleyka.bgrebooter.model.data;

import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Provides;

public class DataManager {
    private DataManager dataManager;
    private SharedPrefsHelper sharedPrefsHelper;

    @Inject
    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
        dataManager = this;
    }

    public void saveAuthKey(String authKey){
        sharedPrefsHelper.put(sharedPrefsHelper.PREF_AUTH_KEY,authKey);
    }

    public String getAuthKey(){
        return sharedPrefsHelper.get(sharedPrefsHelper.PREF_AUTH_KEY,null);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
