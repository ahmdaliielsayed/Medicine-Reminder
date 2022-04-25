package com.ahmdalii.medicinereminder.home.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmdalii.medicinereminder.R;
import com.ahmdalii.medicinereminder.databinding.ActivityHomeBinding;
import com.ahmdalii.medicinereminder.db.room.user.ConcreteLocalSourceUser;
import com.ahmdalii.medicinereminder.healthtaker.view.HealthTakerActivity;
import com.ahmdalii.medicinereminder.home.presenter.HomePresenter;
import com.ahmdalii.medicinereminder.home.presenter.HomePresenterInterface;
import com.ahmdalii.medicinereminder.home.repository.HomeRepo;
import com.ahmdalii.medicinereminder.model.User;
import com.ahmdalii.medicinereminder.network.FirebaseClient;
import com.ahmdalii.medicinereminder.splash.view.MainActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements HomeActivityInterface {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    String userId;

    HomePresenterInterface presenterInterface;

    CircleImageView profile_image;
    TextView txtViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponents();
        presenterInterface.getUserFromRoom(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void initComponents() {
        presenterInterface = new HomePresenter(this,
                HomeRepo.getInstance(ConcreteLocalSourceUser.getInstance(this), FirebaseClient.getInstance()));

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setupToolbar();
        setListeners();
    }

    private void setupToolbar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
    }

    @SuppressLint("NonConstantResourceId")
    private void setListeners() {
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);

            switch (item.getItemId()) {
                case R.id.itemInviteMedFriend:
                    //Toast.makeText(this, "itemInviteMedFriend", Toast.LENGTH_SHORT).show();
                    Intent healthTakerIntent = new Intent(this, HealthTakerActivity.class);
                    healthTakerIntent.putExtra("userId", userId);
                    startActivity(healthTakerIntent);
                    break;
                case R.id.itemLogout:
                    presenterInterface.signOut(this);
                    break;
            }

            item.setChecked(!item.isChecked());

            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayUserInformation(User user) {
        userId = user.getUserId();

        View headerView = navigationView.getHeaderView(0);

        profile_image = headerView.findViewById(R.id.profile_image);
        txtViewName = headerView.findViewById(R.id.txtViewName);

        Glide.with(this)
                .load(user.getProfile_image_uri())
                .into(profile_image);
        txtViewName.setText(user.getUsername());
    }

    @Override
    public void navigateToLoginScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}