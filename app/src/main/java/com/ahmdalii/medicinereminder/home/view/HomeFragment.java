package com.ahmdalii.medicinereminder.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.db.room.medicinedose.ConcreteLocalSourceMedicineDose;
import com.ahmdalii.medicinereminder.home.presenter.HomeFragmentPresenter;
import com.ahmdalii.medicinereminder.home.presenter.HomeFragmentPresenterInterface;
import com.ahmdalii.medicinereminder.home.repository.HomeFragmentRepo;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

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

public class HomeFragment extends Fragment implements HomeFragmentInterface, OnCardClickListener {

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
        homeFragmentAdapter = new HomeFragmentAdapter(view.getContext(), new HashMap<>(), this);
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

    @Override
    public void onCardClick(Medicine medicine, MedicineDose medicineDose) {
        Toast.makeText(view.getContext(), medicine.getName() + "\n" + medicineDose.getStatus(), Toast.LENGTH_SHORT).show();
    }
}