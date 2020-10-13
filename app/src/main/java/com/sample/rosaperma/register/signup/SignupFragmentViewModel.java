package com.sample.rosaperma.register.signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sample.rosaperma.R;
import com.sample.rosaperma.Util;
import com.sample.rosaperma.aws.authentication.AWSLoginHandler;
import com.sample.rosaperma.aws.authentication.AWSLoginModel;
import com.sample.rosaperma.details.AdditionalDetailsActivity;
import com.sample.rosaperma.register.RegisterActivity;

public class SignupFragmentViewModel extends ViewModel implements AWSLoginHandler {
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> emailId = new MutableLiveData<>();
    public MutableLiveData<String> reenterPassword = new MutableLiveData<>();

    private SignupModel signupModel;
    private Context context;
    AWSLoginModel awsLoginModel;
    Util util;

    public SignupFragmentViewModel(){

    }
    /**
     * Constructor for SignupViewModel
     * @param context
     * @param signupModel
     */
    public SignupFragmentViewModel(Context context, SignupModel signupModel) {
        this.signupModel = signupModel;
        this.context = context;
        awsLoginModel = new AWSLoginModel(context, this);
        util = new Util(context);
    }
    public void onSignUpClick() {
        util.showProgressDialog(R.string.signin_in);
        util.hideKeyboard((Activity) context);
        signupModel.setMobileNumber(mobileNumber.getValue());
        signupModel.setPassword(password.getValue());
        signupModel.setUsername(userName.getValue());
        signupModel.setEmailId(emailId.getValue());
        signupModel.setPassword_enter(reenterPassword.getValue());

        if(!signupModel.isValidUserName()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_username, Toast.LENGTH_SHORT).show();
        }
        else if(!signupModel.isValidMobileNumber()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_mobNum, Toast.LENGTH_SHORT).show();
        }

        else if(!signupModel.isValidPassword()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_password, Toast.LENGTH_SHORT).show();
        }

        else if(!signupModel.isValidReenterPassword()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_reenterpassword, Toast.LENGTH_SHORT).show();
        }
        else if(!signupModel.isPasswordsMatch()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.passwords_not_same, Toast.LENGTH_SHORT).show();
        }
        else if(!signupModel.isValidEmailId()) {
            util.dismissProgressDialog();
            Toast.makeText(context, R.string.invalid_emailId, Toast.LENGTH_SHORT).show();
        } else {
            awsLoginModel.registerUser(mobileNumber.getValue(), emailId.getValue(),password.getValue(), userName.getValue() );
        }
    }

    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {
        util.showToast(context,context.getString(R.string.sign_in_successful));
        launchNextScreen();
    }

    @Override
    public void onSignInSuccess() {
    }

    @Override
    public void onFailure(final int process, final Exception exception) {
        Activity activity = (Activity)context;
        activity.runOnUiThread(new Runnable() {
            public void run() {
        util.dismissProgressDialog();
        exception.printStackTrace();
        String whatProcess = "";
        switch (process) {
            case AWSLoginModel.PROCESS_REGISTER:
                whatProcess = "Registration:";
                if(exception.getMessage().contains("UsernameExistsException")) {
                    util.showErrorAlert(context.getResources().getString(R.string.user_already_exixits), context.getString(R.string.error), context.getString(R.string.ok));
                }else {
                    util.showErrorAlert(context.getResources().getString(R.string.sign_up_failed)+exception.getMessage(), context.getString(R.string.error), context.getString(R.string.ok));
                }
                break;
        }}
            });

    }

    private void launchNextScreen(){
        util .dismissProgressDialog();
        Intent i = new Intent(context, RegisterActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }


}