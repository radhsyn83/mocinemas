package com.example.fathurradhy.mocinemas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.LinearLayout;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.utils.UserPreferance;
import com.example.fathurradhy.mocinemas.utils.reminder.AlarmReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingActivity extends AppCompatActivity {
    private SwitchCompat mSwitchNotif;
    private LinearLayout llLanguage;
    private UserPreferance mUserPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mUserPreference = new UserPreferance(PreferenceManager.getDefaultSharedPreferences(this));
        mSwitchNotif = findViewById(R.id.switch_compat);

        mSwitchNotif.setChecked(mUserPreference.getReminderStatus());
        mSwitchNotif.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mUserPreference.getReminderStatus()) {
                mUserPreference.setReminderStatus(false);
                new AlarmReceiver().cancelAlarm(this);
            }
            else {
                mUserPreference.setReminderStatus(true);
                new AlarmReceiver().setRepeatingAlarm(this);
            }
        });

        llLanguage = findViewById(R.id.ll_language);
        llLanguage.setOnClickListener(v -> {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        });

    }
}
