package com.ahmdalii.medicinereminder.home.presenter;

import android.content.Context;

import com.ahmdalii.medicinereminder.home.repository.HomeRepoInterface;
import com.ahmdalii.medicinereminder.home.view.HomeActivityInterface;
import com.ahmdalii.medicinereminder.model.Medicine;
import com.ahmdalii.medicinereminder.model.MedicineDose;
import com.ahmdalii.medicinereminder.network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {

    private final HomeActivityInterface viewInterface;
    private final HomeRepoInterface repoInterface;

    public HomePresenter(HomeActivityInterface viewInterface, HomeRepoInterface repoInterface) {
        this.viewInterface = viewInterface;
        this.repoInterface = repoInterface;
    }

    @Override
    public void getUserFromRoom(Context context) {
        viewInterface.displayUserInformation(repoInterface.getUserFromRoom(context));
    }

    @Override
    public void signOut(Context context) {
        repoInterface.signOut(context);
        viewInterface.navigateToLoginScreen();
    }

    @Override
    public void getAllUnSyncMedicines() {
        viewInterface.syncMedicines(repoInterface.getAllUnSyncMedicines());
    }

    @Override
    public void getAllUnSyncMedicineDoses() {
        viewInterface.syncMedicineDoses(repoInterface.getAllUnSyncMedicineDoses());
    }

    @Override
    public void syncMedicineListToFirebase(List<Medicine> unSyncedMedicines) {
        repoInterface.syncMedicineListToFirebase(this, unSyncedMedicines);
    }

    @Override
    public void syncMedicineDosesListToFirebase(List<MedicineDose> unSyncedMedicineDoses) {
        repoInterface.syncMedicineDosesListToFirebase(this, unSyncedMedicineDoses);
    }

    @Override
    public void onResponse() {

    }

    @Override
    public void onFailure(String error) {
        viewInterface.showSyncError(error);
    }
}
