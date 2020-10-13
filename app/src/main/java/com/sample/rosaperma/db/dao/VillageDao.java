package com.sample.rosaperma.db.dao;

import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.VillageRoomDO;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface VillageDao {

    @Insert
    void insertVillageItem(VillageRoomDO villageRoomDO);

    @Insert
    void insertAllVillage(List<VillageRoomDO> villageRoomDOList);


    @Query("SELECT * FROM village ORDER BY panchayatId asc")
    List<VillageRoomDO> fetchAllVillage();

    @Query("SELECT * FROM village where districtId=:districtId and blockId = :blockId and panchayatId = :panchayatId")
    List<VillageRoomDO> fetchAllVillageforPanchayat(String districtId, String blockId, String panchayatId);

    @Query("DELETE FROM village")
    void deleteAll();

}
