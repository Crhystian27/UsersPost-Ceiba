package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.data.Repository;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;


public class MainActivityViewModel extends ViewModel {

    private LiveData<List<User>> userListLiveData;

    private final Repository repository;

    @Inject
    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    public void fetchUsersInternet(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(View.VISIBLE);
        repository.getUsersInternet(relativeLayout);
    }

    public void fetchUsersDB() {
        if (this.userListLiveData != null) {
            return;
        }
        userListLiveData = repository.getAllUserDB();
    }

    public LiveData<List<User>> getUsersDB() {
        return this.userListLiveData;
    }

}
