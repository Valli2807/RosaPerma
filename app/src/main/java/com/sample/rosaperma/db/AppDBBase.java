package com.sample.rosaperma.db;

import com.sample.rosaperma.db.dao.BlockDao;
import com.sample.rosaperma.db.dao.DistrictDao;
import com.sample.rosaperma.db.dao.PanchayatDao;
import com.sample.rosaperma.db.dao.VillageDao;
import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.DistrictRoomDO;
import com.sample.rosaperma.db.models.PanchayatRoomDO;
import com.sample.rosaperma.db.models.VillageRoomDO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DistrictRoomDO.class, BlockRoomDO.class, PanchayatRoomDO.class, VillageRoomDO.class}, version = 1, exportSchema = false)
public abstract class AppDBBase extends RoomDatabase {
        public abstract BlockDao blockDao();
        public abstract DistrictDao districtDao();
        public abstract PanchayatDao panchayatDao();
        public abstract VillageDao villageDao();
}

