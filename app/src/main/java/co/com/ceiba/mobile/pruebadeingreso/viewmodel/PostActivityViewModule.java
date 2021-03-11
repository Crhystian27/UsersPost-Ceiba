package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.data.Repository;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;

public class PostActivityViewModule extends ViewModel {

    private final Repository repository;
    private LiveData<List<Post>> postListLiveData;

    @Inject
    public PostActivityViewModule(Repository repository) {
        this.repository = repository;
    }

    public void fetchPostsInternet(Integer userId, RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(View.VISIBLE);
        repository.getPostInternet(userId, relativeLayout);
    }

    public void fetchPostsDB(Integer userId) {
        if (this.postListLiveData != null) {
            return;
        }
        postListLiveData = repository.getPostDB(userId);
    }

    public LiveData<List<Post>> getPostDB() {
        return this.postListLiveData;
    }

}
