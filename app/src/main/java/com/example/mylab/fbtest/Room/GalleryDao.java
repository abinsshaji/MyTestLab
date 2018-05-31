package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Abins Shaji on 23/01/18.
 */
@Dao
public interface GalleryDao {
    @Insert
    public List<Long> insertGallery(List<GalleryEntity> galleryEntities);

    @Query("Select * from GalleryEntity LIMIT 15")
    public Single<List<GalleryEntity>> getGallery();
}
