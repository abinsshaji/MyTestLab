package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Abins Shaji on 23/01/18.
 */

public class DbSingleton {
    private Context context;
    private DbSingleton() {
    }
    private static class DbSingletonHelper{
        private static final DbSingleton INSTANCE=new DbSingleton();
    }
    public static DbSingleton getDbSingleton()
    {

        return DbSingletonHelper.INSTANCE;
    }
    public GallleryDatabase initDb(Context context)
    {
        this.context=context;
        GallleryDatabase database= Room.databaseBuilder(context,GallleryDatabase.class,"GalleryDatabase")
                .fallbackToDestructiveMigration()
                .build();
        return database;
    }

}
