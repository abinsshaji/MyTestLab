package com.example.mylab.fbtest.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Abins Shaji on 23/01/18.
 */
@Entity
public class TextEntity {
    @PrimaryKey(autoGenerate = true)
    public  int tid;
    @ColumnInfo(name = "label")
    public String label;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TextEntity(String label) {
        this.label = label;
    }
}
