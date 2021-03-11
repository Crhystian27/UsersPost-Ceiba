package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Endpoints.GET_USERS)
    Single<List<User>> getUsers();

    @GET(Endpoints.GET_POST_USER)
    Single<List<Post>> getPosts(@Query("userId") int userId);
}
