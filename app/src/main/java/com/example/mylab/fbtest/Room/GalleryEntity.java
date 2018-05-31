package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Abins Shaji on 23/01/18.
 */
@Entity
public class GalleryEntity {

    @PrimaryKey(autoGenerate = true)
    private int gid;
    @ColumnInfo(name="filepath")
    private String fpath;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public GalleryEntity(String fpath) {
        this.fpath = fpath;
    }
}
