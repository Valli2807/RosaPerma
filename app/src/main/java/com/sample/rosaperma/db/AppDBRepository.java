package com.sample.rosaperma.db;

import android.content.Context;

import androidx.room.Room;

public class AppDBRepository  {

    private String DB_NAME = "db_rb";

    private AppDBBase prpDatabase;

    public void setPrpDatabase(AppDBBase prpDatabase) {
        this.prpDatabase = prpDatabase;
    }

    public AppDBBase getPrpDatabase() {
        return prpDatabase;
    }

    public AppDBRepository(Context context) {
        prpDatabase = Room.databaseBuilder(context, AppDBBase.class, DB_NAME).build();
    }


}
