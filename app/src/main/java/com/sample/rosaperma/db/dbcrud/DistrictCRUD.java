package com.sample.rosaperma.db.dbcrud;

import android.content.Context;

import com.sample.rosaperma.db.AppDBRepository;
import com.sample.rosaperma.db.RoomDBHandler;
import com.sample.rosaperma.db.models.DistrictRoomDO;

import java.util.ArrayList;
import java.util.List;

public class DistrictCRUD {

    private Context context;
    private RoomDBHandler roomDBHandler;

    public DistrictCRUD(Context context){
        this.context=context;
    }
    public DistrictCRUD(Context context, RoomDBHandler roomDBHandler){
        this.context=context;
        this.roomDBHandler = roomDBHandler;
    }

    public void insertDistrict(){
        DistrictRoomDO districtRoomDO1 = new DistrictRoomDO();
        districtRoomDO1.setDistrictId("1");
        districtRoomDO1.setDistrictName("Ghaziabad");

        DistrictRoomDO districtRoomDO2 = new DistrictRoomDO();
        districtRoomDO2.setDistrictId("2");
        districtRoomDO2.setDistrictName("Varanasi");

        DistrictRoomDO districtRoomDO3 = new DistrictRoomDO();
        districtRoomDO3.setDistrictId("3");
        districtRoomDO3.setDistrictName("Allahabad");

        final List<DistrictRoomDO> districtRoomDOList = new ArrayList<>();
        districtRoomDOList.add(districtRoomDO1);
        districtRoomDOList.add(districtRoomDO2);
        districtRoomDOList.add(districtRoomDO3);

        Thread thread = new Thread() {
            @Override
            public void run() {
                AppDBRepository appDBRepository = new AppDBRepository(context);
                appDBRepository.getPrpDatabase().districtDao().insertAllDistrict(districtRoomDOList);

            }
        };

        thread.start();


    }
}
