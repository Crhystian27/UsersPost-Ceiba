package co.com.ceiba.mobile.pruebadeingreso.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;


public interface RepositoryInterface {

    //Insert Users
    List<User> insertUserDB(List<User> userList) throws Exception;

    //Get All Users
    LiveData<List<User>> getAllUserDB() throws Exception;

    //Insert Post
    List<Post> insertPostDB(List<Post> postList) throws Exception;

    //Get Post From id
    LiveData<List<Post>> getPostDB(Integer id) throws Exception;

}
