package com.sample.rosaperma.details;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.sample.rosaperma.R;
import com.sample.rosaperma.Util;
import com.sample.rosaperma.db.AppDBRepository;
import com.sample.rosaperma.db.dbcrud.BlockCRUD;
import com.sample.rosaperma.db.dbcrud.DistrictCRUD;
import com.sample.rosaperma.db.models.BlockRoomDO;
import com.sample.rosaperma.db.models.DistrictRoomDO;
import com.sample.rosaperma.db.models.PanchayatRoomDO;
import com.sample.rosaperma.db.models.VillageRoomDO;
import com.sample.rosaperma.register.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class DetailsActivity extends AppCompatActivity {
    private Context context;
    Spinner districtSpinner, blockSpinner, panchayatSpinner, villageSpinner;
    List<DistrictRoomDO> districtModel;
    List<BlockRoomDO> blockModel;
    List<PanchayatRoomDO> panchayatModel;
    List<VillageRoomDO> villageModel;
    CardView block_Cardview, panchayat_Cardview, village_Cardview;
    Button next;
     Util util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_details);
        context =this;

         util = new Util(context);


        StatusBarClr();
        setToorBar();


        block_Cardview = (CardView) findViewById(R.id.block_cardview);
        block_Cardview.setVisibility(View.GONE);
        panchayat_Cardview = (CardView) findViewById(R.id.panchayat_cardview);
        panchayat_Cardview.setVisibility(View.GONE);
        village_Cardview = (CardView) findViewById(R.id.villageSpinner_cardview);
        village_Cardview.setVisibility(View.GONE);

        runOnThread("district","","","");
       next = (Button) findViewById(R.id.submit);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(districtSpinner.getSelectedItemPosition()==0||blockSpinner.getSelectedItemPosition()==0||panchayatSpinner.getSelectedItemPosition()==0||villageSpinner.getSelectedItemPosition()==0){
                    util.showToast(context, "All fields are required");
                }else {
                    Intent intent = new Intent(getApplicationContext(), AdditionalDetailsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void runOnThread(final String threadname, final String districtId, final String blockId, final String panchayatId){
        Thread thread = new Thread() {
            @Override
            public void run() {
            if(threadname.equalsIgnoreCase("district"))
                populateDistrictSpinner();
            else if(threadname.equalsIgnoreCase("block"))
                populateBlockSpinner(districtId);
            else if(threadname.equalsIgnoreCase("panchayat"))
                populatePanchayatSpinner(districtId, blockId);
            else if(threadname.equalsIgnoreCase("village"))
                populateVillageSpinner(districtId, blockId, panchayatId);

            }
        };

        thread.start();
    }

    private void setToorBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

    }

    private void populateDistrictSpinner(){
        districtSpinner = (Spinner) findViewById(R.id.districtSpinner);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listDistrict());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
        districtSpinner.setAdapter(adapter);
            }});
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    block_Cardview.setVisibility(View.VISIBLE);
                    panchayat_Cardview.setVisibility(View.GONE);
                    village_Cardview.setVisibility(View.GONE);
                    runOnThread("block",districtModel.get(position-1).getDistrictId(), "", "");
                           // populateBlockSpinner(districtModel.get(position).getDistrictId());

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void populateBlockSpinner(String districtId){
        blockSpinner = (Spinner) findViewById(R.id.blockSpinner);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listBlock(districtId));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                blockSpinner.setAdapter(adapter);
            }});
        blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                  //  populateBlockSpinner();
                    panchayat_Cardview.setVisibility(View.VISIBLE);
                    village_Cardview.setVisibility(View.GONE);
                    runOnThread("panchayat",blockModel.get(position-1).getDistrictId(),blockModel.get(position-1).getBlockId(),"");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void populatePanchayatSpinner(String districtId, String blockId){
        panchayatSpinner = (Spinner) findViewById(R.id.panchayatSpinner);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listPanchayat(districtId, blockId));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                panchayatSpinner.setAdapter(adapter);
            }});
        panchayatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    //  populateBlockSpinner();
                  //  panchayat_Cardview.setVisibility(View.VISIBLE);
                    village_Cardview.setVisibility(View.VISIBLE);
                    runOnThread("village",panchayatModel.get(position-1).getDistrictId(),panchayatModel.get(position-1).getBlockId(),panchayatModel.get(position-1).getPanchayatId());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void populateVillageSpinner(String districtId, String blockId, String panchayatId){
        villageSpinner = (Spinner) findViewById(R.id.villageSpinner);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listVillage(districtId, blockId, panchayatId));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                villageSpinner.setAdapter(adapter);
            }});
        villageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void StatusBarClr(){
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //    window.setStatusBarColor(Color.BLUE);

            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private List<String> listDistrict() {

        AppDBRepository appDBRepository = new AppDBRepository(context);
        districtModel = appDBRepository.getPrpDatabase().districtDao().fetchAllDistrict();
        List<String> items = new ArrayList<>();
        items.add("--Choose--");
        for(int i=0; i<districtModel.size(); i++){
            String districtName =districtModel.get(i).getDistrictName();
            items.add(districtName);
        }
        return  items;
    }

    private List<String> listBlock(String districtId) {

        AppDBRepository appDBRepository = new AppDBRepository(context);

        blockModel= appDBRepository.getPrpDatabase().blockDao().fetchAllBlockforDistrict(districtId);
        List<String> items = new ArrayList<>();
        items.add("--Choose--");
        for(int i=0; i<blockModel.size(); i++){
            String block =blockModel.get(i).getBlockName();
            items.add(block);
        }
        return  items;
    }


    private List<String> listPanchayat(String districtId, String blockId) {

        AppDBRepository appDBRepository = new AppDBRepository(context);

        panchayatModel= appDBRepository.getPrpDatabase().panchayatDao().fetchAllPanchayatforBlock(districtId, blockId);
        List<String> items = new ArrayList<>();
        items.add("--Choose--");
        for(int i=0; i<panchayatModel.size(); i++){
            String panchayat =panchayatModel.get(i).getPanchayatName();
            items.add(panchayat);
        }
        return  items;
    }

    private List<String> listVillage(String districtId, String blockId, String villageId) {

        AppDBRepository appDBRepository = new AppDBRepository(context);

        villageModel= appDBRepository.getPrpDatabase().villageDao().fetchAllVillageforPanchayat(districtId, blockId, villageId);
        List<String> items = new ArrayList<>();
        items.add("--Choose--");
        for(int i=0; i<villageModel.size(); i++){
            String village =villageModel.get(i).getVillageName();
            items.add(village);
        }
        return  items;
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.logut);
      return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logut:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout").setMessage(getString(R.string.logout_confirmation)).setCancelable(false)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                util.clearAll();

                                Intent intent = new Intent(context, RegisterActivity.class);
                                util.showToast(context, getString(R.string.logout_successful));
                                startActivity(intent);
                                finish();

                            }
                        }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
                final AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return true;
        }
    }
}
