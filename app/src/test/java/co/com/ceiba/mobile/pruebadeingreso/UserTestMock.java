package co.com.ceiba.mobile.pruebadeingreso;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.RepositoryInterface;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;

public class UserTestMock implements RepositoryInterface {


    @Override
    public List<User> insertUserDB(List<User> userList) throws Exception {
        if (userList == null) {
            throw new Exception("La lista no puede estar null");
        } else if (userList.isEmpty()) {
            throw new Exception("La lista no puede estar vacia");
        }
        return userList;
    }

    @Override
    public List<Post> insertPostDB(List<Post> postList) throws Exception {
        if (postList == null) {
            throw new Exception("La lista no puede estar null");
        } else if (postList.isEmpty()) {
            throw new Exception("La lista no puede estar vacia");
        }
        return postList;
    }

    @Override
    public LiveData<List<User>> getAllUserDB() throws Exception {

        MutableLiveData<List<User>> user = new MutableLiveData<>();
        user.setValue(getAllUserMock());

        if (user == null) {
            throw new Exception("La lista no puede estar null");
        }
        return user;
    }


    @Override
    public LiveData<List<Post>> getPostDB(Integer id) throws Exception {

        MutableLiveData<List<Post>> post = new MutableLiveData<>();
        post.setValue(getPostMock());

        if (post == null) {
            throw new Exception("La lista no puede estar null");
        }
        return null;
    }


    public static List<Post> getPostMock(){
        return PostTestBuilder.getPostList();
    }

    public static List<User> getAllUserMock() {

        return UserTestBuilder.getUserList();
    }
}
