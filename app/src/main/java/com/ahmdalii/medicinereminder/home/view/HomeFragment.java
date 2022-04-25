package com.ahmdalii.medicinereminder.home.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.NetworkConnection;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.view.AddMedActivity;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.home.presenter.HomeFragmentPresenter;
import com.ahmdalii.medicinereminder.home.presenter.HomeFragmentPresenterInterface;
import com.ahmdalii.medicinereminder.home.repository.HomeFragmentRepo;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class HomeFragment extends Fragment implements HomeFragmentInterface {

    View view;
    Calendar selectedDate;
    Date dateSelected;
    HorizontalCalendar horizontalCalendar;

    HorizontalCalendarView horizontalCalendarView;

    HomeFragmentPresenterInterface presenterInterface;

    ImageView imgViewNoPills;
    TextView txtHome, txtHomeDescription;

    RecyclerView recyclerView;
    HomeFragmentAdapter homeFragmentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        initComponents();

        presenterInterface.getAllDosesWithMedicineName(changeDateFormat(Calendar.getInstance(Locale.US).getTime()));

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                dateSelected = changeDateFormat(date.getTime());
                Log.d("asdfgh:date", "onDateSelected: " + dateSelected.toString());
                presenterInterface.getAllDosesWithMedicineName(dateSelected);
//                allPresenter.getMeds(dateSelected);
            }
        });

        ((FloatingActionButton) view.findViewById(R.id.floatingActionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void initComponents() {

        presenterInterface = new HomeFragmentPresenter(this, HomeFragmentRepo.getInstance(ConcreteLocalSourceMedicineDose.getInstance(view.getContext())));

        imgViewNoPills = view.findViewById(R.id.imgViewNoPills);
        txtHome = view.findViewById(R.id.txtHome);
        txtHomeDescription = view.findViewById(R.id.txtHomeDescription);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeFragmentAdapter = new HomeFragmentAdapter(view.getContext(), new HashMap<>()/*, this*/);
        recyclerView.setAdapter(homeFragmentAdapter);

        selectedDate = Calendar.getInstance();

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendarView = view.findViewById(R.id.calendarView);
        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        horizontalCalendar.selectDate(selectedDate,true);
    }

    private Date changeDateFormat(Date currentDate) {
        Date date = null;
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(currentDate);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            date = format.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public void setDosesToAdapter(Map<Medicine, MedicineDose> allDosesWithMedicineName) {
        Log.d("asdfgh:", String.valueOf(allDosesWithMedicineName.entrySet().size()));

        if (allDosesWithMedicineName.entrySet().size() <= 0) {
            recyclerView.setVisibility(View.GONE);
            imgViewNoPills.setVisibility(View.VISIBLE);
            txtHome.setVisibility(View.VISIBLE);
            txtHomeDescription.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            imgViewNoPills.setVisibility(View.GONE);
            txtHome.setVisibility(View.GONE);
            txtHomeDescription.setVisibility(View.GONE);
        }

        homeFragmentAdapter.setDataToAdapter(allDosesWithMedicineName);
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