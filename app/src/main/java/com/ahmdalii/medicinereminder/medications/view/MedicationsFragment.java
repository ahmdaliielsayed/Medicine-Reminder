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
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivity;
import com.ahmdalii.medicinereminder.editmed.view.EditMedActivity;
import com.ahmdalii.medicinereminder.medicationreminder.view.MedicationReminderActivity;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsPojo;
import com.ahmdalii.medicinereminder.medications.repository.MedicationsSectionPojo;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.model.MedicineUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MedicationsFragment extends Fragment {

    List<MedicationsPojo> activeData;
    List<MedicationsPojo> inactiveData;
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

        testNavigationToDisplay(view);
        //testNavigationToEdit();


        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        activeData = Arrays.asList(new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Clobex", "3 mg", 10, R.drawable.temppill), new MedicationsPojo("Lopid", "5 mg", 5, R.drawable.temppill));
        inactiveData = Arrays.asList(new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Balmex", "7 mg", 9, R.drawable.temppill), new MedicationsPojo("Plavix", "7 mg", 9, R.drawable.temppill));
        data = Arrays.asList(new MedicationsSectionPojo("Active meds", activeData), new MedicationsSectionPojo("Inactive meds", inactiveData));

        MedicationsMainAdapter mainAdapter = new MedicationsMainAdapter(data);
        recyclerView.setAdapter(mainAdapter);

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
                    startActivity(new Intent(getContext(), AddMedActivity.class));
                }
            }
        });

    }

    private void testNavigationToEdit() {
        Medicine medicine = new Medicine();
        medicine.setName("panadol");
        medicine.setUserID("8LBEbybYVRf6Kg3uPF7b9h8d83C2");
        medicine.setSync(true);
        medicine.setActivated(true);
        medicine.setInstructions("before_eating");
        medicine.setReminderMedAmount(2);
        medicine.setReason("COVID-19");
        medicine.setStartDate("2022-04-22");
        medicine.setStrength(1);
        medicine.setUnit(MedicineUnit.g.getUnit());
        medicine.setRefillReminderTime("2022-04-22T16:00");
        medicine.setForm(MedicineForm.PILLS.getForm());
        medicine.setEndDate("2022-04-29");
        medicine.setRemainingMedAmount(22);
        medicine.setDayFrequency(MedicineDayFrequency.EVERYDAY.getFrequency());
        medicine.setTimeFrequency(1);
        medicine.setId("-N0W8rYy4Z0IRSOfRGID");

        Intent intent = new Intent(getActivity(), EditMedActivity.class);
        intent.putExtra("medicine", medicine);
        startActivity(intent);


    }

    private void testNavigationToDisplay(@NonNull View view) {
        Bundle args = new Bundle();
        Medicine medicine = new Medicine();
        medicine.setName("panadol");
        medicine.setUserID("8LBEbybYVRf6Kg3uPF7b9h8d83C2");
        medicine.setSync(true);
        medicine.setActivated(true);
        medicine.setInstructions("before_eating");
        medicine.setReminderMedAmount(2);
        medicine.setReason("COVID-19");
        medicine.setStartDate("2022-04-22");
        medicine.setStrength(1);
        medicine.setUnit(MedicineUnit.g.getUnit());
        medicine.setRefillReminderTime("2022-04-22T16:00");
        medicine.setForm(MedicineForm.PILLS.getForm());
        medicine.setEndDate("2022-04-29");
        medicine.setRemainingMedAmount(22);
        medicine.setDayFrequency(MedicineDayFrequency.EVERYDAY.getFrequency());
        medicine.setTimeFrequency(1);
        medicine.setId("-N0W8rYy4Z0IRSOfRGID");

        args.putSerializable("medicine", medicine);
        Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_displayMedFragment, args);
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