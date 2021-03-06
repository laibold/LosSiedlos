package com.laibold.lossiedlos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laibold.lossiedlos.R;
import com.laibold.lossiedlos.model.Config;
import com.laibold.lossiedlos.model.ResourceCard;
import com.laibold.lossiedlos.model.event.Event;
import com.laibold.lossiedlos.model.event.card.CardEvent;
import com.laibold.lossiedlos.model.event.card.CardEventType;
import com.laibold.lossiedlos.controller.event.EventGenerator;
import com.laibold.lossiedlos.controller.trading.TradingGenerator;
import com.laibold.lossiedlos.persistence.AppRoomDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCountDown;
    private Button buttonStartPause;
    private Button buttonNextPlayer;

    private ImageView tradingGiveImgView;
    private ImageView tradingGetImgView;
    private TextView tradingGiveTextView;
    private ImageView eventImgView;
    private CardView eventOverlayCardView;
    private TextView eventSymbolTextView;
    private TextView eventDescriptionTextView;

    private CountDownTimer timer;
    private boolean timerRunning;
    private long millisLeft;

    private boolean playSound;
    private MediaPlayer nextPlayerSound;
    private MediaPlayer changeSound;
    private MediaPlayer clickSound;

    private TradingGenerator tradingGenerator;
    private EventGenerator eventGenerator;
    private AppRoomDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextPlayerSound = MediaPlayer.create(this, R.raw.next);
        changeSound = MediaPlayer.create(this, R.raw.change);
        clickSound = MediaPlayer.create(this, R.raw.click);

        createConfig();

        configToolbar();
        configTimer();

        configTradingView();
        refreshTradingView(true);

        configEventView();
        refreshEventView(true);
    }


    /**
     * Pause Timer and show Toast on pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (timerRunning){
            pauseTimer();
            Toast.makeText(getApplicationContext(), "Timer pausiert.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Set attributes from config database to prevent frequent queries
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.playSound = database.configDao().getPlaySound();
        tradingGenerator.setChanceOfTradingChange(database.configDao().getChanceOfTradingChange());
        eventGenerator.setChanceOfEventChange(database.configDao().getChanceOfEventChange());
    }

    /**
     * Creates config object and stores it in RoomDatabase
     */
    private void createConfig() {
        database = AppRoomDatabase.getInstance(getApplicationContext());

        if (database.configDao().countEntries() == 0){ // No config set
            Config config = new Config();
            database.configDao().insertConfig(config);
            database.configDao().setChanceOfTradingChange(getResources().getInteger(R.integer.chanceOfTradingChange));
            database.configDao().setChanceOfEventChange(getResources().getInteger(R.integer.chanceOfEventChange));
        }
    }

    /**
     * Set Title on Toolbar
     */
    private void configToolbar(){
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    /**
     * Find views by id and set attributes
     */
    private void configTradingView(){
        this.tradingGenerator = new TradingGenerator(database.configDao().getChanceOfTradingChange());
        this.tradingGiveImgView = findViewById(R.id.trading_give_imageview);
        this.tradingGetImgView = findViewById(R.id.trading_get_imageview);
        this.tradingGiveTextView = findViewById(R.id.traging_give_textview);
    }

    /**
     * set a new trading rate if tradingGenerator random returns true
     * @param force always refresh - for initializing or testing
     * @return if a new trading rate has been set
     */
    private boolean refreshTradingView(boolean force){
        if (force || tradingGenerator.changeTrading()){
            ResourceCard giveCard = tradingGenerator.getGiveCard();
            ResourceCard getCard = tradingGenerator.getGetCard();
            tradingGiveImgView.setImageResource(giveCard.getImageResourceID());
            tradingGetImgView.setImageResource(getCard.getImageResourceID());
            tradingGiveTextView.setText(String.valueOf(tradingGenerator.getGiveRate()));
            return true;
        }
        return false;
    }

    /**
     * Find views by id and set attributes
     */
    private void configEventView(){
        this.eventGenerator = new EventGenerator(this, database.configDao().getChanceOfEventChange());
        this.eventImgView = findViewById(R.id.event_imageview);
        this.eventOverlayCardView = findViewById(R.id.event_overlay_cardview);
        this.eventSymbolTextView = findViewById(R.id.event_symbol_textview);
        this.eventDescriptionTextView = findViewById(R.id.event_description_textview);
    }

    /**
     * set a new event if eventGenerator random returns true
     * @param force always refresh - for initializing or testing
     * @return if a new event has been set
     */
    private boolean refreshEventView(boolean force){
        if (force || eventGenerator.changeEvent()){
            Event event = eventGenerator.getCurrEvent();

            if (event instanceof CardEvent){
                eventSymbolTextView.setText(((CardEvent) event).getSymbolString());
                eventSymbolTextView.setTextColor(getResources().getColor(((CardEvent) event).getColorID(), getTheme()));

                if (((CardEvent) event).getType() == CardEventType.GETNONE){
                    eventOverlayCardView.setVisibility(View.VISIBLE);
                } else {
                    eventOverlayCardView.setVisibility(View.GONE);
                }
            } else {
                eventSymbolTextView.setText("");
                eventOverlayCardView.setVisibility(View.GONE);
            }

            eventImgView.setImageResource(eventGenerator.getCurrImageResourceID());
            eventDescriptionTextView.setText(eventGenerator.getCurrDescription());
            return true;
        }
        return false;
    }

    /**
     * reset timer, refresh trading and event (if random returns true), play sound
     */
    private void nextPlayer(){
        resetTimer();
        boolean changedTradingView = refreshTradingView(false);
        boolean changedEventView = refreshEventView(false);

        if (playSound){
            if (changedTradingView || changedEventView){
                changeSound.start();
            } else{
                clickSound.start();
            }
        }
    }

    /**
     * find timer views and config them
     */
    private void configTimer(){
        this.textViewCountDown = findViewById(R.id.text_view_countdown);
        this.buttonStartPause = findViewById(R.id.button_start_pause);
        this.buttonNextPlayer = findViewById(R.id.button_nextplayer);

        this.millisLeft = getResources().getInteger(R.integer.maxMillis);

        this.buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        this.buttonNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlayer();
            }
        });

        updateCountDownText();
    }

    /**
     * start countdown timer
     */
    private void startTimer() {
        timer = new CountDownTimer(millisLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if(playSound){
                    nextPlayerSound.start();
                }
                nextPlayer();
            }
        }.start();

        buttonNextPlayer.setVisibility(View.VISIBLE);
        timerRunning = true;
        buttonStartPause.setText(getResources().getString(R.string.pause));
    }

    /**
     * pause contdown timer
     */
    private void pauseTimer() {
        if (timer != null){
            timer.cancel();
        }
        timerRunning = false;
        buttonStartPause.setText(getResources().getString(R.string.start));
    }

    /**
     * reset and restart countdown timer
     */
    private void resetTimer() {
        pauseTimer();
        millisLeft = getResources().getInteger(R.integer.maxMillis);
        updateCountDownText();
        buttonStartPause.setVisibility(View.VISIBLE);
        millisLeft += 1000;
        startTimer();
    }

    /**
     * set new timer value
     */
    private void updateCountDownText() {
        int minutes = (int) (millisLeft / 1000) / 60;
        int seconds = (int) (millisLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeLeftFormatted);
    }

    /**
     * start SettingsActivity
     * @param view settings button that is clicked
     */
    public void onSettingsClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
