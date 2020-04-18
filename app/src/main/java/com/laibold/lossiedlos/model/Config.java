package com.laibold.lossiedlos.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class that contains chances of trading and event changes.
 * Set in SettingsActivity, read in MainActivity
 */
@Entity
public class Config {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private int chanceOfTradingChange;

    @ColumnInfo
    private int chanceOfEventChange;

    @ColumnInfo
    private boolean playSound;

    public Config () {
        this.playSound = true;
    }

    /**
     * @return Database id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Database id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get chance that trading rate changes on player change
     * @return value between 0 and 100
     */
    public int getChanceOfTradingChange() {
        return chanceOfTradingChange;
    }


    /**
     * Set chance that trading rate changes on player change
     * @param chanceOfTradingChange value between 0 and 100
     */
    public void setChanceOfTradingChange(int chanceOfTradingChange) {
        this.chanceOfTradingChange = chanceOfTradingChange;
    }

    /**
     * Get chance that trading event changes on player change
     * @return value between 0 and 100
     */
    public int getChanceOfEventChange() {
        return chanceOfEventChange;
    }

    /**
     * Set chance that trading rate changes on player change
     * @param chanceOfEventChange value between 0 and 100
     */
    public void setChanceOfEventChange(int chanceOfEventChange) {
        this.chanceOfEventChange = chanceOfEventChange;
    }

    /**
     * @return if sound is played
     */
    public boolean isPlaySound() {
        return playSound;
    }

    /**
     * @param playSound if sound should be played
     */
    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }
}
