package com.sample.rosaperma.db.dao;

import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.PanchayatRoomDO;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PanchayatDao {

    @Insert
    void insertPanchayatItem(PanchayatRoomDO panchayatRoomDO);

    @Insert
    void insertAllPanchayat(List<PanchayatRoomDO> panchayatRoomDOList);


    @Query("SELECT * FROM panchayat ORDER BY blockId asc")
    List<PanchayatRoomDO> fetchAllPanchayat();

    @Query("SELECT * FROM panchayat where districtId=:districtId and blockId =:blockId")
    List<PanchayatRoomDO> fetchAllPanchayatforBlock(String districtId, String blockId);

    @Query("DELETE FROM panchayat")
    void deleteAll();

}
