package co.com.ceiba.mobile.pruebadeingreso.data;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.PostTestBuilder;
import co.com.ceiba.mobile.pruebadeingreso.UserTestBuilder;
import co.com.ceiba.mobile.pruebadeingreso.UserTestMock;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;

import static org.junit.Assert.*;

public class RepositoryTest {

    private UserTestMock mock;

    @Before
    public void setUp() {
        mock = new UserTestMock();
    }

    @Test
    public void getUsersDB() {

        //Arrange
        List<User> response;
        List<User> data = UserTestMock.getAllUserMock();

        try {
            //Act
            response = mock.getAllUserDB().getValue();

            //Assert
            assertEquals(data, response);
        } catch (Exception ignore) {
        }
    }

    @Test
    public void getPostDB() {

        //Arrange
        List<Post> response;
        List<Post> data = UserTestMock.getPostMock();

        try {
            //Act
            response = mock.getPostDB(1).getValue();

            //Assert
            assertEquals(data, response);
        } catch (Exception ignore) {
        }
    }


    @Test
    public void insertUserDB() throws Exception {

        //Arrange
        List<User> user = new ArrayList<>();
        user.add(new UserTestBuilder().build());
        user.add(new UserTestBuilder().build());
        user.add(new UserTestBuilder().build());

        //Act
        List<User> userResponse = mock.insertUserDB(user);
        System.out.println(userResponse);

        //Assert
        assertEquals(user, userResponse);

    }

    @Test
    public void insertPostDB() throws Exception {

        //Arrange
        List<Post> posts = new ArrayList<>();
        posts.add(new PostTestBuilder().build());
        posts.add(new PostTestBuilder().build());
        posts.add(new PostTestBuilder().build());

        //Act
        List<Post> postResponse = mock.insertPostDB(posts);
        System.out.println(postResponse);

        //Assert
        assertEquals(posts, postResponse);
    }


    @Test
    public void insertUserDB_userListEmpty() {

        //Arrange
        List<User> user = new ArrayList<>();

        try {
            //Act
            mock.insertUserDB(user);
            fail();

        } catch (Exception e) {
            //Assert
            assertEquals("La lista no puede estar vacia", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertPostDB_postListEmpty() {

        //Arrange
        List<Post> post = new ArrayList<>();

        try {
            //Act
            mock.insertPostDB(post);
            fail();

        } catch (Exception e) {
            //Assert
            assertEquals("La lista no puede estar vacia", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertUserDB_userListNull() {

        //Arrange
        List<User> user = null;

        try {
            //Act
            mock.insertUserDB(user);
            fail();


        } catch (Exception e) {
            //Assert
            assertEquals("La lista no puede estar null", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertPostDB_postListNull() {

        //Arrange
        List<Post> posts = null;

        try {
            //Act
            mock.insertPostDB(posts);
            fail();


        } catch (Exception e) {
            //Assert
            assertEquals("La lista no puede estar null", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}