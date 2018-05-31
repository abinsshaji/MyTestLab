package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Abins Shaji on 23/01/18.
 */
@Database(entities = {GalleryEntity.class,TextEntity.class},version = 2)
public abstract class GallleryDatabase extends RoomDatabase {
    public abstract GalleryDao getGalleryDao();
    public  abstract  TextDao getTextDao();
}
