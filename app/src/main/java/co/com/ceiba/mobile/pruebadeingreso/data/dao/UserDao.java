package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;

@Dao
public interface UserDao {

    @Query("SELECT * from user_table ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM user_table")
    void deleteUser();

    @Query("SELECT * FROM user_table WHERE email =:email")
    boolean compareTo(String email);

    @Update(entity = User.class)
    void updateUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<User> userList);

}
