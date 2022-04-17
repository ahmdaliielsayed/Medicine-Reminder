package com.ahmdalii.medicinereminder.notificationdialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.ahmdalii.medicinereminder.R;

public class MyDialog extends Dialog implements View.OnClickListener {


    public MyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

    }

    @Override
    public void onClick(View v) {

    }
}
