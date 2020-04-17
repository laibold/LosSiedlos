package com.laibold.lossiedlos.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.laibold.lossiedlos.model.Config;

@Dao
public interface ConfigDao {
    @Insert
    void insertConfig(Config config);

    @Query("SELECT count(*) FROM Config")
    int countEntries();

    @Query("SELECT chanceOfEventChange FROM Config where id = 0")
    int getChanceOfEventChange();

    @Query("UPDATE Config set chanceOfEventChange = :chanceOfEventChange where id = 0")
    int setChanceOfEventChange(int chanceOfEventChange);

    @Query("SELECT chanceOfTradingChange FROM Config where id = 0")
    int getChanceOfTradingChange();

    @Query("UPDATE Config set chanceOfTradingChange = :chanceOfTradingChange where id = 0")
    int setChanceOfTradingChange(int chanceOfTradingChange);
}
