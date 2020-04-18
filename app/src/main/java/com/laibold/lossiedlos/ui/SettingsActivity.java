package com.laibold.lossiedlos.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.laibold.lossiedlos.R;
import com.laibold.lossiedlos.persistence.AppRoomDatabase;

/**
 * Settings Activity
 */
public class SettingsActivity extends AppCompatActivity {

    private Switch soundSwitch;

    private TextView tradingValueText;
    private SeekBar tradingSeekBar;

    private TextView eventValueText;
    private SeekBar eventSeekBar;


    private AppRoomDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        database = AppRoomDatabase.getInstance(getApplicationContext());

        configSoundSwitch();
        configToolbar();
        configTradingSeekBar();
        configEventSeekBar();
    }

    /**
     * Set title of Toolbar and arrow to get back
     */
    private void configToolbar(){
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.settings_headline));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Configs Seekbar to set chances of trading change
     */
    private void configTradingSeekBar() {
        this.tradingSeekBar = findViewById(R.id.settings_trading_seekbar);
        this.tradingValueText = findViewById(R.id.settings_trading_valuetext);
        tradingSeekBar.setProgress(database.configDao().getChanceOfTradingChange());
        tradingValueText.setText(String.valueOf(tradingSeekBar.getProgress()));

        tradingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tradingValueText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                database.configDao().setChanceOfTradingChange(seekBar.getProgress());
            }
        });
    }

    /**
     * Configs Seekbar to set chances of event change
     */
    private void configEventSeekBar() {
        this.eventSeekBar = findViewById(R.id.settings_event_seekbar);
        this.eventValueText = findViewById(R.id.settings_event_valuetext);
        eventSeekBar.setProgress(database.configDao().getChanceOfEventChange());
        eventValueText.setText(String.valueOf(eventSeekBar.getProgress()));

        eventSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                eventValueText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                database.configDao().setChanceOfEventChange(seekBar.getProgress());
            }
        });
    }

    private void configSoundSwitch(){
        this.soundSwitch = findViewById(R.id.settings_sound_switch);
        soundSwitch.setChecked(database.configDao().getPlaySound());
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                database.configDao().setPlaySound(b);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}