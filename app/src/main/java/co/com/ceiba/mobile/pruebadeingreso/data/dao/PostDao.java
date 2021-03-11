package co.com.ceiba.mobile.pruebadeingreso.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;

@Dao
public interface PostDao {

    @Query("SELECT * from post_table WHERE userId =:userId")
    LiveData<List<Post>> getAllPost(Integer userId);

    @Query("DELETE FROM post_table")
    void deletePost();

    @Query("SELECT * FROM post_table WHERE id =:id")
    boolean compareTo(Integer id);

    @Update(entity = Post.class)
    void updatePost(Post post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(List<Post> postList);

}
