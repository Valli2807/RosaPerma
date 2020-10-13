package com.sample.rosaperma.register.login;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;

public class LoginModel implements Serializable {

    private String mobileNumber;
    private String password;

    public LoginModel(){}
    public LoginModel(String mobileNumber, String password) {
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidMobileNumber() {
        if(this.mobileNumber != null && !TextUtils.isEmpty(this.mobileNumber) && Patterns.PHONE.matcher(this.mobileNumber).matches() && this.mobileNumber.length()==10) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword() {
        if(this.password != null && this.password.length() >= 8) {
            return true;
        }
        return false;
    }
}
