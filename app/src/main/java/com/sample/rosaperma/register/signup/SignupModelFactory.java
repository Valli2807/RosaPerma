package com.sample.rosaperma.register.signup;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SignupModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SignupModel signupModel;
    private Context context;


    public SignupModelFactory(Context context, SignupModel signupModel) {
        this.context = context;
        this.signupModel = signupModel;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SignupFragmentViewModel(context, signupModel);
    }
}