package com.sample.rosaperma.register.signup;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;

public class SignupModel implements Serializable {

    private String mobileNumber;
    private String password;
    private String password_enter;
    private String username;
    private String emailId;

    public String getPassword_enter() {
        return password_enter;
    }

    public void setPassword_enter(String password_enter) {
        this.password_enter = password_enter;
    }


    public SignupModel() {
    }

    public SignupModel(String mobileNumber, String password, String username, String emailId) {
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.username = username;
        this.emailId = emailId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isValidMobileNumber() {
        if (this.mobileNumber != null && !TextUtils.isEmpty(this.mobileNumber) && Patterns.PHONE.matcher(this.mobileNumber).matches() && this.mobileNumber.length()==10) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword() {
        if (this.password != null && this.password.length() >= 8) {
            return true;
        }
        return false;
    }

    public boolean isValidReenterPassword() {
        if (this.password_enter != null && this.password_enter.length() >= 8) {
            return true;
        }
        return false;
    }

    public boolean isPasswordsMatch() {
        if (this.password.equalsIgnoreCase(this.password_enter)) {
            return true;
        }
        return false;
    }

    public boolean isValidUserName() {
        if (this.username != null && !TextUtils.isEmpty(this.username)) {
            return true;
        }
        return false;
    }

    public boolean isValidEmailId(){
        if(this.emailId!=null && !TextUtils.isEmpty(this.emailId)&& Patterns.EMAIL_ADDRESS.matcher(this.emailId).matches()){
            return true;
        }
        return false;
    }

   }
