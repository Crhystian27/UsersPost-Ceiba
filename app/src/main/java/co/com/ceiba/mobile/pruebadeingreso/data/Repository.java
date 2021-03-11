package co.com.ceiba.mobile.pruebadeingreso.data;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.data.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Repository implements RepositoryInterface {

    private final ApiService apiService;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final Executor executor;
    private final UserDao userDao;
    private final PostDao postDao;


    @Inject
    public Repository(ApiService apiService, Executor executor, UserDao userDao, PostDao postDao) {
        this.apiService = apiService;
        this.executor = executor;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public void getPostInternet(Integer userId, RelativeLayout relativeLayout) {
        executor.execute(() -> compositeDisposable.add(apiService.getPosts(userId)
                .observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(postList -> {
                            new Handler().postDelayed(() -> relativeLayout.setVisibility(View.GONE), 800);
                            insertPostDB(postList);
                        },
                        throwable -> {
                            new Handler().postDelayed(() -> relativeLayout.setVisibility(View.GONE), 800);

                        })));
    }


    @Override
    public List<Post> insertPostDB(List<Post> postList) throws Exception {
        if (postList == null || postList.isEmpty()) {
            throw new Exception();
        } else {
            for (Post post : postList) {
                if (!postDao.compareTo(post.getId())) {
                    postDao.insertPost(postList);
                }
            }
        }
        return postList;
    }

    @Override
    public LiveData<List<Post>> getPostDB(Integer userId) {
        return postDao.getAllPost(userId);
    }


    public void getUsersInternet(RelativeLayout relativeLayout) {
        executor.execute(() -> compositeDisposable.add(apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userList -> {
                            new Handler().postDelayed(() -> relativeLayout.setVisibility(View.GONE), 800);
                            insertUserDB(userList);

                        },
                        throwable -> {
                            Log.e(throwable.toString(), throwable.getMessage());
                            new Handler().postDelayed(() -> relativeLayout.setVisibility(View.GONE), 800);
                        })));
    }

    @Override
    public List<User> insertUserDB(List<User> userList) throws Exception {
        if (userList == null || userList.isEmpty()) {
            throw new Exception();
        } else {
            for (User user : userList) {
                if (!userDao.compareTo(user.getEmail())) {
                    userDao.insertUser(userList);
                }
            }
        }
        return userList;
    }

    @Override
    public LiveData<List<User>> getAllUserDB() {
        return userDao.getAllUsers();
    }

}
