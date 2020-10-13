package com.sample.rosaperma.register.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginModel loginModel;
    private Context context;


    public LoginModelFactory(Context context, LoginModel loginModel) {
        this.context = context;
        this.loginModel = loginModel;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LoginFragmentViewModel(context, loginModel);
    }
}