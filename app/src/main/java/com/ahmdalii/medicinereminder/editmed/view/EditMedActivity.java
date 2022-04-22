package com.ahmdalii.medicinereminder.editmed.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.ahmdalii.medicinereminder.JSONSerializer;
import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.editmed.presenter.EditMedPresenter;
import com.ahmdalii.medicinereminder.editmed.presenter.EditMedPresenterInterface;
import com.ahmdalii.medicinereminder.model.Medicine;

public class EditMedActivity extends AppCompatActivity implements EditMedActivityInterface {

    EditMedPresenterInterface editMedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med);

        editMedPresenter = new EditMedPresenter(this);

        editMedPresenter.setMedicine((Medicine) getIntent().getSerializableExtra("medicine"));
        //editMedPresenter.setDoses(JSONSerializer.deserializeMedicineDoses(getIntent().getStringExtra("doses")));

        ((EditText) findViewById(R.id.edit_text_med_name_edit_med)).setText(editMedPresenter.getMedicine().getName());

        setUI();
    }

    private void setUI() {

    }

    @Override
    public Context getViewContext() {
        return this;
    }
}