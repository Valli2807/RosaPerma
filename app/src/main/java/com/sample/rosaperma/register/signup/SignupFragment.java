package com.sample.rosaperma.register.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.rosaperma.R;
import com.sample.rosaperma.databinding.FragmentSignupBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SignupFragment extends Fragment {

    private SignupFragmentViewModel mViewModel;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        FragmentSignupBinding binding = DataBindingUtil.inflate(inflater, R
                .layout.fragment_signup, container, false);

        // Create instance for ViewModel class
       // mViewModel = ViewModelProviders.of(this).get(SignupFragmentViewModel.class);
        mViewModel = ViewModelProviders.of(this, new SignupModelFactory(getActivity(), new SignupModel())).get(SignupFragmentViewModel.class);
        //Set ViewModel instance to binding class
       binding.setSignupFragmentViewModel(mViewModel);

        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignupFragmentViewModel.class);
        // TODO: Use the ViewModel
    }



}
