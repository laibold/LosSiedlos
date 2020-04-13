package com.laibold.lossiedlos.model;

/**
 * Singleton class that contains chances of trading and event changes.
 * Set in SettingsActivity, read in MainActivity
 */
public class Config {

    private static Config instance;

    private int chanceOfTradingChange = -1;
    private int chanceOfEventChange = -1;

    private Config () {}

    /**
     * get singleton instance
     * @return instance of Config
     */
    public static Config getInstance(){
        if (Config.instance == null){
            Config.instance = new Config();
        }
        return Config.instance;
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
}
