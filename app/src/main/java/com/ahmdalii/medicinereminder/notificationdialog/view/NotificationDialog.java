package com.ahmdalii.medicinereminder.notificationdialog.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.notificationdialog.presenter.NotificationDialogPresenter;
import com.ahmdalii.medicinereminder.notificationdialog.presenter.NotificationDialogPresenterInterface;

public class NotificationDialog extends Dialog implements NotificationDialogInterface, View.OnClickListener {

    Context context;
    NotificationDialogPresenterInterface notificationDialogPresenter;
    LifecycleOwner lifecycleOwner;

    public NotificationDialog(@NonNull Context context, Medicine medicine, MedicineDose dose, LifecycleOwner lifecycleOwner) {
        super(context);
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        Log.i("TAG", "NotificationDialog: " + medicine);
        Log.i("TAG", "NotificationDialog: " + dose);
        medicine.setId(dose.getMedID());
        notificationDialogPresenter = new NotificationDialogPresenter(this, medicine, dose);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        ((TextView) findViewById(R.id.text_view_med_name_notification_dialog)).setText(notificationDialogPresenter.getMedicine().getName());

        if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.PILLS.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_pills);
        }
        else if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.SOLUTION.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_solution);
        }
        else if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.DROPS.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_drops);
        }
        else if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.INHALER.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_inhaler);
        }
        else if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.TOPICAL.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_topical);
        }
        else if(notificationDialogPresenter.getMedicine().getForm().equals(MedicineForm.POWDER.getForm())) {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_powder);
        }
        else {
            ((ImageView) findViewById(R.id.image_view_med_icon_notification_dialog)).setImageResource(R.drawable.ic_injection);
        }


        ((TextView) findViewById(R.id.text_view_quantity_notification_dialog)).setText(notificationDialogPresenter.getMedicine().getStrength() + notificationDialogPresenter.getMedicine().getUnit());
        ((TextView) findViewById(R.id.text_view_time_notification_dialog)).setText(notificationDialogPresenter.getDose().getTime());

        ((CardView) findViewById(R.id.card_view_skip_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationDialogPresenter.skipDose();
                cancel();
            }
        });

        ((CardView) findViewById(R.id.card_view_take_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationDialogPresenter.takeDose();
                cancel();
            }
        });



        ((ImageView) findViewById(R.id.image_view_delete_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notificationDialogPresenter.deleteDose();
                //cancel();
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        ((ImageView) findViewById(R.id.image_view_edit_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        ((ImageView) findViewById(R.id.image_view_info_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        ((CardView) findViewById(R.id.card_view_reschedule_notification_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showRefillReminderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setMessage("You should refill " + notificationDialogPresenter.getMedicine().getName())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
}
