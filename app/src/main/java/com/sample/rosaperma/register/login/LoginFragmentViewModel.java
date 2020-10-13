package com.sample.rosaperma.register.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sample.rosaperma.R;
import com.sample.rosaperma.Util;
import com.sample.rosaperma.aws.authentication.AWSLoginHandler;
import com.sample.rosaperma.aws.authentication.AWSLoginModel;
import com.sample.rosaperma.db.dbcrud.BlockCRUD;
import com.sample.rosaperma.db.dbcrud.DistrictCRUD;
import com.sample.rosaperma.db.dbcrud.PanchayatCRUD;
import com.sample.rosaperma.db.dbcrud.VillageCRUD;
import com.sample.rosaperma.details.AdditionalDetailsActivity;
import com.sample.rosaperma.details.DetailsActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginFragmentViewModel extends ViewModel implements AWSLoginHandler{
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private LoginModel loginModel;
    private Context context;
    AWSLoginModel awsLoginModel;
    Util util;

    public LoginFragmentViewModel(){

    }
    /**
     * Constructor for LoginViewModel
     * @param context
     * @param loginModel
     */
    public LoginFragmentViewModel(Context context, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.context = context;
        awsLoginModel = new AWSLoginModel(context, this);
        util = new Util(this.context);
   }
    /**
     * Get Mutable Data instance for progress bar
     * @return
     */

    public void onLoginClick() {
        util.showProgressDialog(R.string.logging_in);
        util.hideKeyboard((Activity) context);
        loginModel.setMobileNumber(mobileNumber.getValue());
        loginModel.setPassword(password.getValue());

        if(!loginModel.isValidMobileNumber()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_mobNum, Toast.LENGTH_SHORT).show();
        }

        else if(!loginModel.isValidPassword()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_password, Toast.LENGTH_SHORT).show();
        }
        else {
               awsLoginModel.signInUser(mobileNumber.getValue(), password.getValue());
        }
    }

    @Override
    public void onFailure(int process, final Exception exception) {

        exception.printStackTrace();
        String whatProcess = "";
        switch (process) {
            case AWSLoginModel.PROCESS_SIGN_IN:
                whatProcess = "Sign In:";
                util.dismissProgressDialog();
                if (exception.getMessage().contains("UserNotFoundException")) {
                    util.showErrorAlert(context.getResources().getString(R.string.user_does_not_exist), context.getString(R.string.error), context.getString(R.string.ok));

                } else if (exception.getMessage().contains("NotAuthorizedException")) {
                    util.showErrorAlert(context.getResources().getString(R.string.incorrect_username_or_password), context.getString(R.string.error), context.getString(R.string.ok));
                } else {
                    //util.showErrorAlert(context.getResources().getString(R.string.login_failed)+exception.getMessage(), context.getString(R.string.error), context.getString(R.string.ok));
                    launchNextScreen();
                }
                break;
        }
    }
    @Override
    public void onSignInSuccess() {
        launchNextScreen();
    }
    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {

    }
    private void launchNextScreen(){
        initialiseDB();
        util.showToast(context,context.getString(R.string.log_in_successful));
        util.dismissProgressDialog();
        Intent i = new Intent(context, DetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }


    private void initialiseDB() {
        DistrictCRUD districtCRUD = new DistrictCRUD(context);
        districtCRUD.insertDistrict();

        BlockCRUD blockCRUD = new BlockCRUD(context);
        blockCRUD.insertBlock();

        PanchayatCRUD panchayatCRUD = new PanchayatCRUD(context);
        panchayatCRUD.insertPanchayat();

        VillageCRUD villageCRUD = new VillageCRUD(context);
        villageCRUD.insertVillage();
    }
}
