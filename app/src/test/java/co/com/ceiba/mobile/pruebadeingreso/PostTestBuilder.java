package co.com.ceiba.mobile.pruebadeingreso;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;

public class PostTestBuilder {

    public Integer id;
    public Integer userId;
    public String title;
    public String body;
    public static List<Post> list;

    public PostTestBuilder() {
        this.id = (int) (Math.random() * 100);
        this.userId = (int) (Math.random() * 100);
        this.title = "Post 01";
        this.body = "Body of the post 01 ";
    }

    public Post build() {
        return new Post(id, userId, title, body);
    }

    public static List<Post> getPostList() {
        list = new ArrayList<>();
        list.add(new PostTestBuilder().build());
        list.add(new PostTestBuilder().build());
        list.add(new PostTestBuilder().build());
        return list;
    }

}
