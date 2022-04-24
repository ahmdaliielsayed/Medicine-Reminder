package com.ahmdalii.medicinereminder.medicationreminder.view;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ahmdalii.medicinereminder.Constants;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.db.room.user.ConcreteLocalSourceUser;
import com.ahmdalii.medicinereminder.medicationreminder.presenter.MedicationReminderPresenter;
import com.ahmdalii.medicinereminder.medicationreminder.presenter.MedicationReminderPresenterInterface;
import com.ahmdalii.medicinereminder.medicationreminder.repository.MedicationReminderRepo;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.User;
import com.bumptech.glide.Glide;

import java.time.LocalDateTime;

import de.hdodenhof.circleimageview.CircleImageView;

public class MedicationReminderActivity extends AppCompatActivity implements MedicationReminderInterface {

    MediaPlayer mediaPlayerSong;

    CircleImageView profile_image;
    TextView txtViewPersonalDescription, txtViewDoseTime, txtViewDoseName, txtViewDoseDescription;
    CardView cardViewSkip, cardViewTake, cardViewSnooze;

    Medicine medicine;
    MedicineDose dose;
    LocalDateTime dateTime;

    MedicationReminderPresenterInterface presenterInterface;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_reminder);

        //                              width                               height
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFinishOnTouchOutside(false);

        initComponents();
        presenterInterface.getUserFromRoom(this);
        displayCardInfo();

        cardViewSkip.setOnClickListener(view -> updateDose(DoseStatus.SKIPPED.getStatus()));
        cardViewTake.setOnClickListener(view -> updateDose(DoseStatus.TAKEN.getStatus()));
        cardViewSnooze.setOnClickListener(view -> {
            mediaPlayerSong.stop();
            dose.setStatus(DoseStatus.UNKNOWN.getStatus());
            dose.setSync(false);

            new Thread(() -> {
                // this is executed on another Thread
                presenterInterface.snoozeDose(dose, medicine, this);

                // create a Handler associated with the main Thread
                Handler handler = new Handler(Looper.getMainLooper());
                // post a Runnable to the main Thread
                // this is executed on the main Thread
                handler.post(() -> {
                    Toast.makeText(this, R.string.snooze_for_five_minutes, Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();
        });
    }

    private void updateDose(String status) {
        mediaPlayerSong.stop();

        dose.setStatus(status);
        dose.setSync(false);

        new Thread(() -> {
            // this is executed on another Thread
            presenterInterface.updateDose(dose, MedicationReminderActivity.this);

            // create a Handler associated with the main Thread
            Handler handler = new Handler(Looper.getMainLooper());
            // post a Runnable to the main Thread
            // this is executed on the main Thread
            handler.post(MedicationReminderActivity.this::finish);
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initComponents() {
        presenterInterface = new MedicationReminderPresenter(this,
                MedicationReminderRepo.getInstance(ConcreteLocalSourceUser.getInstance(this), ConcreteLocalSourceMedicineDose.getInstance(this)));

        profile_image = findViewById(R.id.profile_image);

        txtViewPersonalDescription = findViewById(R.id.txtViewPersonalDescription);
        txtViewDoseTime = findViewById(R.id.txtViewDoseTime);
        txtViewDoseName = findViewById(R.id.txtViewDoseName);
        txtViewDoseDescription = findViewById(R.id.txtViewDoseDescription);

        cardViewSkip = findViewById(R.id.cardViewSkip);
        cardViewTake = findViewById(R.id.cardViewTake);
        cardViewSnooze = findViewById(R.id.cardViewSnooze);

        medicine = (Medicine) getIntent().getSerializableExtra(Constants.MEDICINE_KEY);
        dose = (MedicineDose) getIntent().getSerializableExtra(Constants.DOSE_KEY);

        dateTime = LocalDateTime.parse(dose.getTime());

        mediaPlayerSong = MediaPlayer.create(this, R.raw.cucko);
        mediaPlayerSong.setLooping(true);
        mediaPlayerSong.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void displayUserInformation(User user) {
        Glide.with(this)
                .load(user.getProfile_image_uri())
                .into(profile_image);

        txtViewPersonalDescription.setText(getString(R.string.hello).concat(" ")
                .concat(user.getUsername()).concat(", ")
                .concat(getString(R.string.time_to_take)).concat(" ")
                .concat(dateTime.getHour() + ":" + dateTime.getMinute()).concat(" ")
                .concat(getString(R.string.meds)));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayCardInfo() {
        txtViewDoseTime.setText(String.valueOf(dateTime.getHour()).concat(":").concat(String.valueOf(dateTime.getMinute())));
        txtViewDoseName.setText(medicine.getName());
        txtViewDoseDescription.setText(String.valueOf(medicine.getStrength()).concat(" ")
                .concat(medicine.getUnit()).concat(", ")
                .concat(getString(R.string.take)).concat(" ")
                .concat(String.valueOf(dose.getAmount())).concat(" ")
                .concat(getString(R.string.before_eating)).concat(" ").concat(dose.getTime()));
    }
}