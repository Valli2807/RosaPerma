package com.sample.rosaperma.details;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sample.rosaperma.R;
import com.sample.rosaperma.Util;
import com.sample.rosaperma.register.RegisterActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

public class AdditionalDetailsActivity extends AppCompatActivity {
    private Context context;
    Button back, submit;
    TextInputEditText nameEt, mobilenumberEt,whatsappnumberEt,addressEdit;
    AppCompatSpinner spinner;
    Util util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = this;
         util= new Util(context);

        StatusBarClr();
        setToorBar();

        setLayout();

        spinner = (AppCompatSpinner) findViewById(R.id.postSpinner);

        back = (Button) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.submit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItemPosition()==0||nameEt.getText().toString().equalsIgnoreCase("")||mobilenumberEt.getText().toString().equalsIgnoreCase("")||whatsappnumberEt.getText().toString().equalsIgnoreCase("")||addressEdit.getText().toString().equalsIgnoreCase("")){
                    util.showToast(context, "All fields are required");
                }else {
                    util.showToast(context, "Your form submitted successfully!!!");
                    Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    public void setLayout(){
        final TextInputLayout nameLayout = (TextInputLayout) findViewById(R.id.name);
        nameLayout.setErrorEnabled(true);
        nameEt = (TextInputEditText) findViewById(R.id.editname);
        nameEt.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                //When EditText losses focus
                if (b) {
                    nameLayout.setHint("");
                    nameLayout.setError("");

                } else {
                    if (nameEt.getText().toString().equalsIgnoreCase("")) {    // or any other validation
                        nameLayout.setHint("");
                        nameLayout.setError(context.getString(R.string.this_field_required));
                    }else{
                        nameLayout.setHint("");
                    }
                }

            }});


        final TextInputLayout mobilenumberLayout = (TextInputLayout) findViewById(R.id.mobnumber);
        mobilenumberLayout.setErrorEnabled(true);
        mobilenumberEt = (TextInputEditText) findViewById(R.id.editMobileNumber);
        mobilenumberEt.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                //When EditText losses focus
                if (b) {
                    mobilenumberLayout.setHint("");
                    mobilenumberLayout.setError("");

                } else {
                    if (mobilenumberEt.getText().toString().equalsIgnoreCase("")) {    // or any other validation
                        mobilenumberLayout.setHint("");
                        mobilenumberLayout.setError(context.getString(R.string.this_field_required));
                    }else{
                        mobilenumberLayout.setHint("");
                    }
                }

            }});


        final TextInputLayout whatsappnumberLayout = (TextInputLayout) findViewById(R.id.whatsapp);
        whatsappnumberLayout.setErrorEnabled(true);
        whatsappnumberEt = (TextInputEditText) findViewById(R.id.editwhatapp);
        whatsappnumberEt.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                //When EditText losses focus
                if (b) {
                    whatsappnumberLayout.setHint("");
                    whatsappnumberLayout.setError("");

                } else {
                    if (whatsappnumberEt.getText().toString().equalsIgnoreCase("")) {    // or any other validation
                        whatsappnumberLayout.setHint("");
                        whatsappnumberLayout.setError(context.getString(R.string.this_field_required));
                    }else{
                        whatsappnumberLayout.setHint("");
                    }
                }

            }});


        final TextInputLayout addressLayout = (TextInputLayout) findViewById(R.id.address);
        addressLayout.setErrorEnabled(true);
        addressEdit = (TextInputEditText) findViewById(R.id.editaddress);
        addressEdit.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                //When EditText losses focus
                if (b) {
                    addressLayout.setHint("");
                    addressLayout.setError("");

                } else {
                    if (addressEdit.getText().toString().equalsIgnoreCase("")) {    // or any other validation
                        addressLayout.setHint("");
                        addressLayout.setError(context.getString(R.string.this_field_required));
                    }else{
                        addressLayout.setHint("");
                    }
                }

            }});
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

    private void setToorBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
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