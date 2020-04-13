package com.laibold.lossiedlos.ui;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.laibold.lossiedlos.R;
import com.laibold.lossiedlos.model.Config;

/**
 * Settings Activity
 */
public class SettingsActivity extends AppCompatActivity {

    private TextView tradingValueText;
    private SeekBar tradingSeekBar;

    private TextView eventValueText;
    private SeekBar eventSeekBar;

    private Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.config = Config.getInstance();

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
        tradingSeekBar.setProgress(config.getChanceOfTradingChange());
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
                config.setChanceOfTradingChange(seekBar.getProgress());
            }
        });
    }

    /**
     * Configs Seekbar to set chances of event change
     */
    private void configEventSeekBar() {
        this.eventSeekBar = findViewById(R.id.settings_event_seekbar);
        this.eventValueText = findViewById(R.id.settings_event_valuetext);
        eventSeekBar.setProgress(config.getChanceOfEventChange());
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
                config.setChanceOfEventChange(seekBar.getProgress());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}