package com.ahmdalii.medicinereminder.register.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.home.view.HomeActivity;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.register.presenter.RegisterPresenter;
import com.ahmdalii.medicinereminder.register.presenter.RegisterPresenterInterface;
import com.ahmdalii.medicinereminder.register.repository.RegisterRepo;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterFragment extends Fragment implements RegisterFragmentInterface {

    private View view;
    private RegisterPresenterInterface presenterInterface;

    private CircleImageView profile_image;
    private ProgressBar progressBarImg;

    private Button btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        initComponents();

        profile_image.setOnClickListener(view1 -> getProfileImage());
        btnRegister.setOnClickListener(view12 -> startActivity(new Intent(getActivity(), HomeActivity.class)));
    }

    private void initComponents() {
        presenterInterface = new RegisterPresenter(this,
                RegisterRepo.getInstance(FirebaseClient.getInstance(), view.getContext()));

        profile_image = view.findViewById(R.id.profile_image);
        progressBarImg = view.findViewById(R.id.progressBarImg);

        btnRegister = view.findViewById(R.id.btnRegister);
    }

    private void getProfileImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryActivityResultLauncher.launch(galleryIntent);
    }

    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // here we will handle the result of our intent
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // image picked ... get uri of image
                    Intent data = result.getData();
                    Uri uriProfileImage = Objects.requireNonNull(data).getData();

                    profile_image.setImageURI(uriProfileImage);
                    progressBarImg.setVisibility(View.VISIBLE);
                    presenterInterface.uploadProfileImage(uriProfileImage);
                } else {
                    // cancelled
                    Toast.makeText(view.getContext(), R.string.img_picked_cancelled, Toast.LENGTH_SHORT).show();
                }
            }
    );
}