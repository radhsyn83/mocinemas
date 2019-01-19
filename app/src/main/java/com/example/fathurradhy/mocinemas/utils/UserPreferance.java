package com.example.fathurradhy.mocinemas.utils;

import android.content.SharedPreferences;

public class UserPreferance {
    private final SharedPreferences mSharedPreferences;

    private static final String REMINDER_STATUS = "reminder_status";
    private static final String FIRST_INTSALL = "first_install";

    public UserPreferance(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public Boolean getReminderStatus(){
        return mSharedPreferences.getBoolean(REMINDER_STATUS, true);
    }

    public void setReminderStatus(Boolean isOn) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putBoolean(REMINDER_STATUS,isOn);
        editor.apply();
    }

    public Boolean getFirstInstall(){
        return mSharedPreferences.getBoolean(FIRST_INTSALL, true);
    }

    public void setFirstIntsall() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putBoolean(FIRST_INTSALL, false);
        editor.apply();
    }

}
