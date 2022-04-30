package com.ahmdalii.medicinereminder.displaymed.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.addmed.model.MedicineDayFrequency;
import com.ahmdalii.medicinereminder.addmed.model.MedicineForm;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedPresenter;
import com.ahmdalii.medicinereminder.displaymed.presenter.DisplayMedPresenterInterface;
import com.ahmdalii.medicinereminder.editmed.view.EditMedActivity;
import com.ahmdalii.medicinereminder.model.DoseStatus;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class DisplayMedFragment extends Fragment implements DisplayMedFragmentInterface {

    DisplayMedPresenterInterface displayMedPresenter;
    View view;

    ProgressDialog dialog;


    public DisplayMedFragment() {

    }

    public static DisplayMedFragment newInstance(String param1, String param2) {
        DisplayMedFragment fragment = new DisplayMedFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("TAggggG", "onResume: ");

        setPresenter();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("TAggggG", "onCreateView: ");
        return inflater.inflate(R.layout.fragment_display_med, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        this.view = view;
        Log.i("TAggggG", "onViewCreated: ");
        dialog = new ProgressDialog(getContext());
        dialog.show();
        setPresenter();
        //setUI();
    }

    private void setPresenter() {
        displayMedPresenter = new DisplayMedPresenter(this);
        displayMedPresenter.getStoredMedicineAndDoses(getArguments().getString("medicineID"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUI() {

        if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.PILLS.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_pills);
        }
        else if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.SOLUTION.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_solution);
        }
        else if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.DROPS.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_drops);
        }
        else if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.INHALER.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_inhaler);
        }
        else if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.TOPICAL.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_topical);
        }
        else if(displayMedPresenter.getMedicine().getForm().equals(MedicineForm.POWDER.getForm())) {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_powder);
        }
        else {
            ((ImageView) view.findViewById(R.id.image_view_med_icon_display_med)).setImageResource(R.drawable.ic_injection);
        }

        ((ImageView) view.findViewById(R.id.icon_edit_display_med_toolbar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditMedActivity.class);
                intent.putExtra("medicine", displayMedPresenter.getMedicine());
                intent.putExtra("doses", JSONSerializer.serializeMedicineDoses(displayMedPresenter.getDoses()));
                startActivity(intent);
            }
        });

        ((ImageView) view.findViewById(R.id.icon_delete_display_med_toolbar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext())
                        .setMessage("Are you sure?")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                displayMedPresenter.deleteMedicine();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create().show();
            }
        });

        ((TextView) view.findViewById(R.id.text_view_med_name_display_med)).setText(displayMedPresenter.getMedicine().getName());
        ((TextView) view.findViewById(R.id.text_view_strength_display_med)).setText(displayMedPresenter.getMedicine().getStrength() + displayMedPresenter.getMedicine().getUnit());

        if(displayMedPresenter.getMedicine().getDayFrequency().equals(MedicineDayFrequency.EVERYDAY.getFrequency())) {
            ((TextView) view.findViewById(R.id.text_view_day_frequency_display_med)).setText("Everyday");
        }
        else if(displayMedPresenter.getMedicine().getDayFrequency().equals(MedicineDayFrequency.EVERY_NUMBER_OF_DAYS.getFrequency())) {
            ((TextView) view.findViewById(R.id.text_view_day_frequency_display_med)).setText("Every " + displayMedPresenter.getMedicine().getEveryNDays() + " days");
        }
        else {
            ((TextView) view.findViewById(R.id.text_view_day_frequency_display_med)).setText(displayMedPresenter.getMedicine().getWeekDays());
        }

        ((TextView) view.findViewById(R.id.text_view_remaining_amount_display_med)).setText(displayMedPresenter.getMedicine().getRemainingMedAmount() + "");
        ((TextView) view.findViewById(R.id.text_view_remind_count_display_med)).setText(displayMedPresenter.getMedicine().getReminderMedAmount() + "");

        if(displayMedPresenter.getDoses().size() != 0) {
            setLastTakenTextView(view, displayMedPresenter.getDoses());
            setTimesTextViews(view, displayMedPresenter.getDoses());
            setAmountsTextViews(view, displayMedPresenter.getDoses());
        }

        Button suspendButton = view.findViewById(R.id.button_suspend_display_med);
        Button refillButton = view.findViewById(R.id.button_refill_display_med);

        if(displayMedPresenter.getMedicine().getActivated()) {
            suspendButton.setText("Suspend");
        }
        else {
            suspendButton.setText("Activate");
        }
        suspendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(displayMedPresenter.getMedicine().getActivated()) {
                    suspendButton.setText("Activate");
                    displayMedPresenter.getMedicine().setActivated(false);
                    displayMedPresenter.removeOneTimeWorkRequest();
                }
                else {
                    suspendButton.setText("Suspend");
                    displayMedPresenter.getMedicine().setActivated(true);
                    displayMedPresenter.addOneTimeWorkRequest();
                }
                displayMedPresenter.updateMedicine();
            }
        });

        refillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: DIALOG");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialog = inflater.inflate(R.layout.refill_amount_dialog, null);
                builder.setView(dialog)
                        .setTitle("Refill")
                        .setMessage("You have " + displayMedPresenter.getMedicine().getRemainingMedAmount() + " meds remaining")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                displayMedPresenter.getMedicine().setRemainingMedAmount(
                                        displayMedPresenter.getMedicine().getRemainingMedAmount() +
                                                Integer.parseInt(((EditText) dialog.findViewById(R.id.edit_text_add_amount_refill_amount_dialog)).getText().toString())
                                );
                                displayMedPresenter.updateMedicine();
                                setUI();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void setLastTakenTextView(@NonNull View view, ArrayList<MedicineDose> doses) {
        String lastTakenString = "Unknown";
        for(int i = doses.size() - 1; i >= 0; i--) {
            if(doses.get(i).getStatus().equals(DoseStatus.TAKEN.getStatus())) {
                Log.i("TAG", "setLastTakenTextView: ");
                lastTakenString = doses.get(i).getTime();
                break;
            }
        }

        ((TextView) view.findViewById(R.id.text_view_last_taken_display_med)).setText(lastTakenString);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setTimesTextViews(@NonNull View view, ArrayList<MedicineDose> doses) {
        ArrayList<String> dosesTimes = new ArrayList<>();
        String firstDay = LocalDateTime.parse(doses.get(0).getTime()).toLocalDate().toString();
        for(MedicineDose dose: doses) {
            if(firstDay.equals(LocalDateTime.parse(dose.getTime()).toLocalDate().toString())) {
                dosesTimes.add(LocalDateTime.parse(dose.getTime()).toLocalTime().truncatedTo(ChronoUnit.MINUTES).toString());
            }
        }



        if(dosesTimes.size() == 1) {
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setText(dosesTimes.get(0));
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_time_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesTimes.size() == 2) {
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setText(dosesTimes.get(0));
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setText(dosesTimes.get(1));
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_time_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesTimes.size() == 3) {
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setText(dosesTimes.get(0));
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setText(dosesTimes.get(1));
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setText(dosesTimes.get(2));
            ((TextView) view.findViewById(R.id.text_view_time_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesTimes.size() == 4) {
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_4_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_time_1_display_med)).setText(dosesTimes.get(0));
            ((TextView) view.findViewById(R.id.text_view_time_2_display_med)).setText(dosesTimes.get(1));
            ((TextView) view.findViewById(R.id.text_view_time_3_display_med)).setText(dosesTimes.get(2));
            ((TextView) view.findViewById(R.id.text_view_time_4_display_med)).setText(dosesTimes.get(3));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setAmountsTextViews(@NonNull View view, ArrayList<MedicineDose> doses) {
        ArrayList<Integer> dosesAmounts = new ArrayList<>();
        String firstDay = LocalDateTime.parse(doses.get(0).getTime()).toLocalDate().toString();
        for(MedicineDose dose: doses) {
            if(firstDay.equals(LocalDateTime.parse(dose.getTime()).toLocalDate().toString())) {
                dosesAmounts.add(dose.getAmount());
            }
        }

        if(dosesAmounts.size() == 1) {
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setText(dosesAmounts.get(0) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_4_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_2_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesAmounts.size() == 2) {
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setText(dosesAmounts.get(0) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setText(dosesAmounts.get(1) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_4_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_3_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesAmounts.size() == 3) {
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setText(dosesAmounts.get(0) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setText(dosesAmounts.get(1) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setText(dosesAmounts.get(2) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_4_display_med)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_take_4_display_med)).setVisibility(View.GONE);
        }
        else if(dosesAmounts.size() == 4) {
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_1_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_2_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_3_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_4_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_take_4_display_med)).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text_view_dose_amount_1_display_med)).setText(dosesAmounts.get(0) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_2_display_med)).setText(dosesAmounts.get(1) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_3_display_med)).setText(dosesAmounts.get(2) + "");
            ((TextView) view.findViewById(R.id.text_view_dose_amount_4_display_med)).setText(dosesAmounts.get(3) + "");
        }
    }


    @Override
    public Context getViewContext() {
        return getContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void refreshView() {
        setUI();
    }

    @Override
    public void showProgressBar() {
        dialog.show();    }

    @Override
    public void hideProgressBar() {
        dialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeView() {
        Navigation.findNavController(view).popBackStack();
    }

    @Override
    public LifecycleOwner getViewLifecycle() {
        return getViewLifecycleOwner();
    }
}