package com.sample.rosaperma.db.dbcrud;

import android.content.Context;

import com.sample.rosaperma.db.AppDBRepository;
import com.sample.rosaperma.db.RoomDBHandler;
import com.sample.rosaperma.db.models.PanchayatRoomDO;
import com.sample.rosaperma.db.models.VillageRoomDO;

import java.util.ArrayList;
import java.util.List;

public class VillageCRUD {

    private Context context;
    private RoomDBHandler roomDBHandler;

    public VillageCRUD(Context context){
        this.context=context;
    }
    public VillageCRUD(Context context, RoomDBHandler roomDBHandler){
        this.context=context;
        this.roomDBHandler = roomDBHandler;
    }

    public void insertVillage(){
        VillageRoomDO villageRoomDO1 = new VillageRoomDO();
        villageRoomDO1.setDistrictId("1");
        villageRoomDO1.setBlockId("1");
        villageRoomDO1.setPanchayatId("1");
        villageRoomDO1.setVillageId("1");
        villageRoomDO1.setVillageName("Inayatpur");

        VillageRoomDO villageRoomDO2 = new VillageRoomDO();
        villageRoomDO2.setDistrictId("1");
        villageRoomDO2.setBlockId("1");
        villageRoomDO2.setPanchayatId("1");
        villageRoomDO2.setVillageId("2");
        villageRoomDO2.setVillageName("Anfpur");

        VillageRoomDO villageRoomDO3 = new VillageRoomDO();
        villageRoomDO3.setDistrictId("1");
        villageRoomDO3.setBlockId("1");
        villageRoomDO3.setPanchayatId("2");
        villageRoomDO3.setVillageId("1");
        villageRoomDO3.setVillageName("Jalalabad");

        VillageRoomDO villageRoomDO4 = new VillageRoomDO();
        villageRoomDO4.setDistrictId("1");
        villageRoomDO4.setBlockId("1");
        villageRoomDO4.setPanchayatId("2");
        villageRoomDO4.setVillageId("2");
        villageRoomDO4.setVillageName("Bhawanipur");


        VillageRoomDO villageRoomDO5 = new VillageRoomDO();
        villageRoomDO5.setDistrictId("1");
        villageRoomDO5.setBlockId("2");
        villageRoomDO5.setPanchayatId("1");
        villageRoomDO5.setVillageId("1");
        villageRoomDO5.setVillageName("Atrauli");

        VillageRoomDO villageRoomDO6 = new VillageRoomDO();
        villageRoomDO6.setDistrictId("1");
        villageRoomDO6.setBlockId("2");
        villageRoomDO6.setPanchayatId("1");
        villageRoomDO6.setVillageId("2");
        villageRoomDO6.setVillageName("Avalpur");

        VillageRoomDO villageRoomDO7 = new VillageRoomDO();
        villageRoomDO7.setDistrictId("1");
        villageRoomDO7.setBlockId("2");
        villageRoomDO7.setPanchayatId("2");
        villageRoomDO7.setVillageId("1");
        villageRoomDO7.setVillageName("Shamli");

        VillageRoomDO villageRoomDO8 = new VillageRoomDO();
        villageRoomDO8.setDistrictId("1");
        villageRoomDO8.setBlockId("2");
        villageRoomDO8.setPanchayatId("2");
        villageRoomDO8.setVillageId("2");
        villageRoomDO8.setVillageName("Afrashahpur");


        VillageRoomDO villageRoomDO9 = new VillageRoomDO();
        villageRoomDO9.setDistrictId("2");
        villageRoomDO9.setBlockId("1");
        villageRoomDO9.setPanchayatId("1");
        villageRoomDO9.setVillageId("1");
        villageRoomDO9.setVillageName("Raipur");

        VillageRoomDO villageRoomDO10 = new VillageRoomDO();
        villageRoomDO10.setDistrictId("2");
        villageRoomDO10.setBlockId("1");
        villageRoomDO10.setPanchayatId("1");
        villageRoomDO10.setVillageId("2");
        villageRoomDO10.setVillageName("Chak Gajhiya");

        VillageRoomDO villageRoomDO11 = new VillageRoomDO();
        villageRoomDO11.setDistrictId("2");
        villageRoomDO11.setBlockId("1");
        villageRoomDO11.setPanchayatId("2");
        villageRoomDO11.setVillageId("1");
        villageRoomDO11.setVillageName("Tilvar");

        VillageRoomDO villageRoomDO12 = new VillageRoomDO();
        villageRoomDO12.setDistrictId("2");
        villageRoomDO12.setBlockId("1");
        villageRoomDO12.setPanchayatId("2");
        villageRoomDO12.setVillageId("2");
        villageRoomDO12.setVillageName("Virshapur");


        VillageRoomDO villageRoomDO13 = new VillageRoomDO();
        villageRoomDO13.setDistrictId("2");
        villageRoomDO13.setBlockId("2");
        villageRoomDO13.setPanchayatId("1");
        villageRoomDO13.setVillageId("1");
        villageRoomDO13.setVillageName("Gola");

        VillageRoomDO villageRoomDO14 = new VillageRoomDO();
        villageRoomDO14.setDistrictId("2");
        villageRoomDO14.setBlockId("2");
        villageRoomDO14.setPanchayatId("1");
        villageRoomDO14.setVillageId("2");
        villageRoomDO14.setVillageName("Cholapur");

        VillageRoomDO villageRoomDO15 = new VillageRoomDO();
        villageRoomDO15.setDistrictId("2");
        villageRoomDO15.setBlockId("2");
        villageRoomDO15.setPanchayatId("2");
        villageRoomDO15.setVillageId("1");
        villageRoomDO15.setVillageName("Deipur");

        VillageRoomDO villageRoomDO16 = new VillageRoomDO();
        villageRoomDO16.setDistrictId("2");
        villageRoomDO16.setBlockId("2");
        villageRoomDO16.setPanchayatId("2");
        villageRoomDO16.setVillageId("2");
        villageRoomDO16.setVillageName("Bhavanipur");

        VillageRoomDO villageRoomDO17 = new VillageRoomDO();
        villageRoomDO17.setDistrictId("3");
        villageRoomDO17.setBlockId("1");
        villageRoomDO17.setPanchayatId("1");
        villageRoomDO17.setVillageId("1");
        villageRoomDO17.setVillageName("Lotad");

        VillageRoomDO villageRoomDO18 = new VillageRoomDO();
        villageRoomDO18.setDistrictId("3");
        villageRoomDO18.setBlockId("1");
        villageRoomDO18.setPanchayatId("1");
        villageRoomDO18.setVillageId("2");
        villageRoomDO18.setVillageName("Bhuska");

        VillageRoomDO villageRoomDO19 = new VillageRoomDO();
        villageRoomDO19.setDistrictId("3");
        villageRoomDO19.setBlockId("1");
        villageRoomDO19.setPanchayatId("2");
        villageRoomDO19.setVillageId("1");
        villageRoomDO19.setVillageName("Bairghat");

        VillageRoomDO villageRoomDO20 = new VillageRoomDO();
        villageRoomDO20.setDistrictId("3");
        villageRoomDO20.setBlockId("1");
        villageRoomDO20.setPanchayatId("2");
        villageRoomDO20.setVillageId("2");
        villageRoomDO20.setVillageName("Derhan");


        VillageRoomDO villageRoomDO21 = new VillageRoomDO();
        villageRoomDO21.setDistrictId("3");
        villageRoomDO21.setBlockId("2");
        villageRoomDO21.setPanchayatId("1");
        villageRoomDO21.setVillageId("1");
        villageRoomDO21.setVillageName("Belapur");

        VillageRoomDO villageRoomDO22 = new VillageRoomDO();
        villageRoomDO22.setDistrictId("3");
        villageRoomDO22.setBlockId("2");
        villageRoomDO22.setPanchayatId("1");
        villageRoomDO22.setVillageId("2");
        villageRoomDO22.setVillageName("Itraura");

        VillageRoomDO villageRoomDO23 = new VillageRoomDO();
        villageRoomDO23.setDistrictId("3");
        villageRoomDO23.setBlockId("2");
        villageRoomDO23.setPanchayatId("2");
        villageRoomDO23.setVillageId("1");
        villageRoomDO23.setVillageName("Chak Ataullah");

        VillageRoomDO villageRoomDO24 = new VillageRoomDO();
        villageRoomDO24.setDistrictId("3");
        villageRoomDO24.setBlockId("2");
        villageRoomDO24.setPanchayatId("2");
        villageRoomDO24.setVillageId("2");
        villageRoomDO24.setVillageName("Ghoghapur");


        final List<VillageRoomDO> villageRoomDOList = new ArrayList<>();
        villageRoomDOList.add(villageRoomDO1);
        villageRoomDOList.add(villageRoomDO2);
        villageRoomDOList.add(villageRoomDO3);
        villageRoomDOList.add(villageRoomDO4);
        villageRoomDOList.add(villageRoomDO5);
        villageRoomDOList.add(villageRoomDO6);
        villageRoomDOList.add(villageRoomDO7);
        villageRoomDOList.add(villageRoomDO8);
        villageRoomDOList.add(villageRoomDO9);
        villageRoomDOList.add(villageRoomDO10);
        villageRoomDOList.add(villageRoomDO11);
        villageRoomDOList.add(villageRoomDO12);
        villageRoomDOList.add(villageRoomDO13);
        villageRoomDOList.add(villageRoomDO14);
        villageRoomDOList.add(villageRoomDO15);
        villageRoomDOList.add(villageRoomDO16);
        villageRoomDOList.add(villageRoomDO17);
        villageRoomDOList.add(villageRoomDO18);
        villageRoomDOList.add(villageRoomDO19);
        villageRoomDOList.add(villageRoomDO20);
        villageRoomDOList.add(villageRoomDO21);
        villageRoomDOList.add(villageRoomDO22);
        villageRoomDOList.add(villageRoomDO23);
        villageRoomDOList.add(villageRoomDO24);




        Thread thread = new Thread() {
            @Override
            public void run() {
                AppDBRepository appDBRepository = new AppDBRepository(context);
                appDBRepository.getPrpDatabase().villageDao().insertAllVillage(villageRoomDOList);

            }
        };

        thread.start();


    }
}
