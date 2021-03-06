package com.ahmdalii.medicinereminder.medications.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivity;
import com.ahmdalii.medicinereminder.editmed.view.EditMedActivity;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.medications.presenter.MedicationsPresenter;
import com.ahmdalii.medicinereminder.medications.presenter.MedicationsPresenterInterface;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsLocalSource;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsRepository;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedsPojo;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.MedicineUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MedicationsFragment extends Fragment implements MedicationsViewInterface {

    MedicationsPresenterInterface presenter;

//    List<MedicationsPojo> activeData;
//    List<MedicationsPojo> inactiveData;

    MedicationsMainAdapter mainAdapter;
    List<MedsPojo> activeData;
    List<MedsPojo> inactiveData;
    List<MedicationsSectionPojo> data;
    RecyclerView recyclerView;

    Button addMedBtn;
    View view;

    public MedicationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        recyclerView = view.findViewById(R.id.recyclerViewId);
        addMedBtn = view.findViewById(R.id.addMedId);

        presenter = new MedicationsPresenter(this, MedicationsRepository.getInstance(getContext(), MedicationsLocalSource.getInstance(getContext())));

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //data =  Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));

        data = Arrays.asList(new MedicationsSectionPojo("Active meds", new ArrayList<>()), new MedicationsSectionPojo("Inactive meds", new ArrayList<>()));
        mainAdapter = new MedicationsMainAdapter(data);
        recyclerView.setAdapter(mainAdapter);

        presenter.getActiveMeds().observe(this, new Observer<List<MedsPojo>>() {
            @Override
            public void onChanged(List<MedsPojo> medsPojos) {
                activeData = medsPojos;
                data =  Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));
                mainAdapter.setList(data);
                mainAdapter.notifyDataSetChanged();
            }
        });

        presenter.getInactiveMeds().observe(this, new Observer<List<MedsPojo>>() {
            @Override
            public void onChanged(List<MedsPojo> medsPojos) {
                inactiveData = medsPojos;
                data =  Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));
                mainAdapter.setList(data);
                mainAdapter.notifyDataSetChanged();
            }
        });
        //static data
        //activeData = Arrays.asList(new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Lopid", "5 mg", 5, R.drawable.temppill));
        //inactiveData = Arrays.asList(new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Plavix", "7 mg", 9, R.drawable.temppill));



        addMedBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("BatteryLife")
            @Override
            public void onClick(View view) {
                Log.i("emy", "onClick: you clicked");

                PowerManager pm = (PowerManager) view.getContext().getSystemService(Context.POWER_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && !Settings.canDrawOverlays(view.getContext())) {
                    runtimePermissionForUser();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && !pm.isIgnoringBatteryOptimizations(view.getContext().getPackageName())) {
                    Intent intent = new Intent();
                    intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.setData(Uri.parse("package:" + view.getContext().getPackageName()));
                    startActivity(intent);
                } else {
                    if(NetworkConnection.isNetworkAvailable(getContext())) {
                        startActivity(new Intent(getContext(), AddMedActivity.class));
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                .setMessage("No Connection")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                        builder.create().show();

                    }
                }
            }
        });

    }


    public void runtimePermissionForUser() {
        if (!Settings.canDrawOverlays(view.getContext())) {
            if ("xiaomi".equals(Build.MANUFACTURER.toLowerCase(Locale.ROOT))) {
                final Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter",
                        "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", view.getContext().getPackageName());

                new AlertDialog.Builder(view.getContext())
                        .setTitle(R.string.additional_permissions)
                        .setMessage(R.string.additional_permissions_description)
                        .setPositiveButton(R.string.go_to_settings, (dialog, which) -> startActivity(intent))
                        .setIcon(R.drawable.ic_warning)
                        .show();
            } else {
                new AlertDialog.Builder(view.getContext())
                        .setTitle(R.string.warning)
                        .setMessage(R.string.error_msg_permission_required)
                        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                            Intent permissionIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:" + view.getContext().getPackageName()));

                            runtimePermissionResultLauncher.launch(permissionIntent);
                        })
                        .setIcon(R.drawable.ic_warning)
                        .show();
            }
        }
    }

    private final ActivityResultLauncher<Intent> runtimePermissionResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {

            }
    );
}