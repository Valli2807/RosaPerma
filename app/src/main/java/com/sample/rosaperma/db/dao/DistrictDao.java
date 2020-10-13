package com.sample.rosaperma.db.dao;

import com.sample.rosaperma.db.models.DistrictRoomDO;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DistrictDao {

    @Insert
    void insertDistrictItem(DistrictRoomDO districtRoomDO);

    @Insert
    void insertAllDistrict(List<DistrictRoomDO> districtRoomDOList);


    @Query("SELECT * FROM district ORDER BY districtId asc")
    List<DistrictRoomDO> fetchAllDistrict();

    @Query("DELETE FROM district")
    void deleteAll();
}
