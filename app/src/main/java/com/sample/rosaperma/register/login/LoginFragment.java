package com.sample.rosaperma.register.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.rosaperma.R;
import com.sample.rosaperma.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class LoginFragment extends Fragment {

    private LoginFragmentViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R
               .layout.fragment_login, container, false);

        // Create instance for ViewModel class
        //mViewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel.class);
         mViewModel = ViewModelProviders.of(this, new LoginModelFactory(getActivity(), new LoginModel())).get(LoginFragmentViewModel.class);
        //Set ViewModel instance to binding class
        binding.setLoginFragmentViewModel(mViewModel);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Create instance for data binding auto generated class file

    }

}
