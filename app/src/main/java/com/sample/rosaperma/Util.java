package com.sample.rosaperma;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sample.rosaperma.db.AppDBRepository;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Util {
    private Context context;
    private ProgressDialog progressDialog;
    public Util(Context context){
        this.context = context;
    }

    public void showProgressDialog(int msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(msg));
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Set the progress dialog background color transparent
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressDialog() {

    if (null!=progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    public void showErrorAlert(String msg, String title, String ok) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title).setMessage(msg).setCancelable(false).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                }
            });
            final AlertDialog alert = builder.create();
            alert.show();
    }

    public void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public void clearAll(){
        clearPreferences();
        clearDB();
    }

    public void clearPreferences(){
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.IS_USER_LOGGED_IN, false);
        editor.putString(Constants.PREFERENCE_USER_NAME, "");
        editor.putString(Constants.PREFERENCE_MOBILE_NO, "");
        editor.putString(Constants.PREFERENCE_USER_EMAIL, "");
        editor.apply();
    }
    public void clearDB() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<Integer> result = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                AppDBRepository appDBRepository = new AppDBRepository(context);
                appDBRepository.getPrpDatabase().districtDao().deleteAll();
                appDBRepository.getPrpDatabase().blockDao().deleteAll();
                appDBRepository.getPrpDatabase().panchayatDao().deleteAll();
                appDBRepository.getPrpDatabase().villageDao().deleteAll();
                return 0;
            }
        });
    }
    public void hideKeyboard(Activity activity) {
        View v = activity.getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm.isAcceptingText())  // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


}
