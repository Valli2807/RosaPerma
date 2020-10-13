package com.sample.rosaperma.db.dao;

import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.DistrictRoomDO;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BlockDao {

    @Insert
    void insertBlockItem(BlockRoomDO blockRoomDO);

    @Insert
    void insertAllBlock(List<BlockRoomDO> blockRoomDOList);


    @Query("SELECT * FROM block ORDER BY blockId asc")
    List<BlockRoomDO> fetchAllBlock();

    @Query("SELECT * FROM block where districtId=:districtId")
    List<BlockRoomDO> fetchAllBlockforDistrict(String districtId);

    @Query("DELETE FROM block")
    void deleteAll();
}
