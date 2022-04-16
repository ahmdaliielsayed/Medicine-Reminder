package com.ahmdalii.medicinereminder.login.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmdalii.medicinereminder.R;

public class LoginFragment extends Fragment {

    private View view;
    private TextView txtViewRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        initComponents();

        txtViewRegister.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_loginFragment_to_registerFragment));
    }

    private void initComponents() {
        txtViewRegister = view.findViewById(R.id.txtViewRegister);
    }
}