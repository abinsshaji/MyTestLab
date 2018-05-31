package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Abins Shaji on 23/01/18.
 */
@Dao
public interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(TextEntity textEntity);

    @Query("select label from TextEntity  where tid=(select max(tid) from TextEntity)")
    Flowable<String> getTextData();
}
