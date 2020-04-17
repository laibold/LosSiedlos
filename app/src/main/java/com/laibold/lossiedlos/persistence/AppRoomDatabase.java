package com.laibold.lossiedlos.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.laibold.lossiedlos.R;

import com.laibold.lossiedlos.model.Config;

@Database(entities = {Config.class, }, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static String DB_NAME;
    private static volatile AppRoomDatabase instance;

    public static synchronized AppRoomDatabase getInstance(Context context){
        if (instance == null){
            String dbName = context.getApplicationContext().getResources().getString(R.string.database_name);
            DB_NAME = dbName;
            instance = create(context);
        }
        return instance;
    }

    private static AppRoomDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppRoomDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract ConfigDao configDao();
}