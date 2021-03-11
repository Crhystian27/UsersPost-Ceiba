package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

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

    public void fetchPostsInternet(Integer userId) {
        repository.getPostInternet(userId);
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
