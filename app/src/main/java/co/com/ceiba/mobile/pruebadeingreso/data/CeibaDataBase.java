package co.com.ceiba.mobile.pruebadeingreso.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.com.ceiba.mobile.pruebadeingreso.data.dao.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;

@Database(entities = {User.class, Post.class}, version = 1, exportSchema = false)

public abstract class CeibaDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract PostDao postDao();
}
