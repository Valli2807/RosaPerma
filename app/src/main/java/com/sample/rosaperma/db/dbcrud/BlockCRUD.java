package com.sample.rosaperma.db.dbcrud;

import android.content.Context;

import com.sample.rosaperma.db.AppDBRepository;
import com.sample.rosaperma.db.RoomDBHandler;
import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.DistrictRoomDO;

import java.util.ArrayList;
import java.util.List;

public class BlockCRUD {

    private Context context;
    private RoomDBHandler roomDBHandler;

    public BlockCRUD(Context context){
        this.context=context;
    }
    public BlockCRUD(Context context, RoomDBHandler roomDBHandler){
        this.context=context;
        this.roomDBHandler = roomDBHandler;
    }

    public void insertBlock(){
        BlockRoomDO blockRoomDO1 = new BlockRoomDO();
        blockRoomDO1.setDistrictId("1");
        blockRoomDO1.setBlockId("1");
        blockRoomDO1.setBlockName("Rajpur");

        BlockRoomDO blockRoomDO2 = new BlockRoomDO();
        blockRoomDO2.setDistrictId("1");
        blockRoomDO2.setBlockId("2");
        blockRoomDO2.setBlockName("Bhojpur");

        BlockRoomDO blockRoomDO3 = new BlockRoomDO();
        blockRoomDO3.setDistrictId("2");
        blockRoomDO3.setBlockId("1");
        blockRoomDO3.setBlockName("Baragon");

        BlockRoomDO blockRoomDO4 = new BlockRoomDO();
        blockRoomDO4.setDistrictId("2");
        blockRoomDO4.setBlockId("2");
        blockRoomDO4.setBlockName("Cholapur");

        BlockRoomDO blockRoomDO5 = new BlockRoomDO();
        blockRoomDO5.setDistrictId("3");
        blockRoomDO5.setBlockId("1");
        blockRoomDO5.setBlockName("Meja");

        BlockRoomDO blockRoomDO6 = new BlockRoomDO();
        blockRoomDO6.setDistrictId("3");
        blockRoomDO6.setBlockId("2");
        blockRoomDO6.setBlockName("Chaka");

        final List<BlockRoomDO> blockRoomDOList = new ArrayList<>();
        blockRoomDOList.add(blockRoomDO1);
        blockRoomDOList.add(blockRoomDO2);
        blockRoomDOList.add(blockRoomDO3);
        blockRoomDOList.add(blockRoomDO4);
        blockRoomDOList.add(blockRoomDO5);
        blockRoomDOList.add(blockRoomDO6);

        Thread thread = new Thread() {
            @Override
            public void run() {
                AppDBRepository appDBRepository = new AppDBRepository(context);
                appDBRepository.getPrpDatabase().blockDao().insertAllBlock(blockRoomDOList);

            }
        };

        thread.start();



    }
}
