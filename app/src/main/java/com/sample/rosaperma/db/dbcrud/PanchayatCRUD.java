package com.sample.rosaperma.db.dbcrud;

import android.content.Context;

import com.sample.rosaperma.db.AppDBRepository;
import com.sample.rosaperma.db.RoomDBHandler;
import com.sample.rosaperma.db.models.DistrictRoomDO;
import com.sample.rosaperma.db.models.PanchayatRoomDO;

import java.util.ArrayList;
import java.util.List;

public class PanchayatCRUD {

    private Context context;
    private RoomDBHandler roomDBHandler;

    public PanchayatCRUD(Context context){
        this.context=context;
    }
    public PanchayatCRUD(Context context, RoomDBHandler roomDBHandler){
        this.context=context;
        this.roomDBHandler = roomDBHandler;
    }

    public void insertPanchayat(){
        PanchayatRoomDO panchayatRoomDO1 = new PanchayatRoomDO();
        panchayatRoomDO1.setDistrictId("1");
        panchayatRoomDO1.setBlockId("1");
        panchayatRoomDO1.setPanchayatId("1");
        panchayatRoomDO1.setPanchayatName("Inayatpur");

        PanchayatRoomDO panchayatRoomDO2 = new PanchayatRoomDO();
        panchayatRoomDO2.setDistrictId("1");
        panchayatRoomDO2.setBlockId("1");
        panchayatRoomDO2.setPanchayatId("2");
        panchayatRoomDO2.setPanchayatName("Jalalabad");

        PanchayatRoomDO panchayatRoomDO3 = new PanchayatRoomDO();
        panchayatRoomDO3.setDistrictId("1");
        panchayatRoomDO3.setBlockId("2");
        panchayatRoomDO3.setPanchayatId("1");
        panchayatRoomDO3.setPanchayatName("Atrauli");

        PanchayatRoomDO panchayatRoomDO4 = new PanchayatRoomDO();
        panchayatRoomDO4.setDistrictId("1");
        panchayatRoomDO4.setBlockId("2");
        panchayatRoomDO4.setPanchayatId("2");
        panchayatRoomDO4.setPanchayatName("Shamli");


        PanchayatRoomDO panchayatRoomDO5 = new PanchayatRoomDO();
        panchayatRoomDO5.setDistrictId("2");
        panchayatRoomDO5.setBlockId("1");
        panchayatRoomDO5.setPanchayatId("1");
        panchayatRoomDO5.setPanchayatName("Raipur");

        PanchayatRoomDO panchayatRoomDO6 = new PanchayatRoomDO();
        panchayatRoomDO6.setDistrictId("2");
        panchayatRoomDO6.setBlockId("1");
        panchayatRoomDO6.setPanchayatId("2");
        panchayatRoomDO6.setPanchayatName("Tilvar");

        PanchayatRoomDO panchayatRoomDO7 = new PanchayatRoomDO();
        panchayatRoomDO7.setDistrictId("2");
        panchayatRoomDO7.setBlockId("2");
        panchayatRoomDO7.setPanchayatId("1");
        panchayatRoomDO7.setPanchayatName("Cholapur");

        PanchayatRoomDO panchayatRoomDO8 = new PanchayatRoomDO();
        panchayatRoomDO8.setDistrictId("2");
        panchayatRoomDO8.setBlockId("2");
        panchayatRoomDO8.setPanchayatId("2");
        panchayatRoomDO8.setPanchayatName("Bhavanpur");


        PanchayatRoomDO panchayatRoomDO9 = new PanchayatRoomDO();
        panchayatRoomDO9.setDistrictId("3");
        panchayatRoomDO9.setBlockId("1");
        panchayatRoomDO9.setPanchayatId("1");
        panchayatRoomDO9.setPanchayatName("Lotad");

        PanchayatRoomDO panchayatRoomDO10 = new PanchayatRoomDO();
        panchayatRoomDO10.setDistrictId("3");
        panchayatRoomDO10.setBlockId("1");
        panchayatRoomDO10.setPanchayatId("2");
        panchayatRoomDO10.setPanchayatName("Derhan");

        PanchayatRoomDO panchayatRoomDO11 = new PanchayatRoomDO();
        panchayatRoomDO11.setDistrictId("3");
        panchayatRoomDO11.setBlockId("2");
        panchayatRoomDO11.setPanchayatId("1");
        panchayatRoomDO11.setPanchayatName("Balapur");

        PanchayatRoomDO panchayatRoomDO12 = new PanchayatRoomDO();
        panchayatRoomDO12.setDistrictId("3");
        panchayatRoomDO12.setBlockId("2");
        panchayatRoomDO12.setPanchayatId("2");
        panchayatRoomDO12.setPanchayatName("Ghoghapur");





        final List<PanchayatRoomDO> panchayatRoomDOList = new ArrayList<>();
        panchayatRoomDOList.add(panchayatRoomDO1);
        panchayatRoomDOList.add(panchayatRoomDO2);
        panchayatRoomDOList.add(panchayatRoomDO3);
        panchayatRoomDOList.add(panchayatRoomDO4);
        panchayatRoomDOList.add(panchayatRoomDO5);
        panchayatRoomDOList.add(panchayatRoomDO6);
        panchayatRoomDOList.add(panchayatRoomDO7);
        panchayatRoomDOList.add(panchayatRoomDO8);
        panchayatRoomDOList.add(panchayatRoomDO9);
        panchayatRoomDOList.add(panchayatRoomDO10);
        panchayatRoomDOList.add(panchayatRoomDO11);
        panchayatRoomDOList.add(panchayatRoomDO12);

        Thread thread = new Thread() {
            @Override
            public void run() {
                AppDBRepository appDBRepository = new AppDBRepository(context);
                appDBRepository.getPrpDatabase().panchayatDao().insertAllPanchayat(panchayatRoomDOList);

            }
        };

        thread.start();


    }
}
