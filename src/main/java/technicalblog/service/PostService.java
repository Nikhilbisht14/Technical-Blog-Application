package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAllPost(){
//        ArrayList<Post> posts = new ArrayList<>();
//
//        Post post1 = new Post();
//        post1.setTitle("Post 1");
//        post1.setBody("Post Body 1");
//        post1.setDate(new Date());
//
//        Post post2 = new Post();
//        post2.setTitle("Post 2");
//        post2.setBody("Post Body 2");
//        post2.setDate(new Date());
//
//        Post post3 = new Post();
//        post3.setTitle("Post 3");
//        post3.setBody("Post Body 3");
//        post3.setDate(new Date());
//
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);


//        Connection connection = null;
//        try {
//            //Driver name
//            Class.forName("org.postgresql.Driver");
//            //JDBC API for the connection with the DB.
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres","nikhil14");
//            //Statement object to fire the command such as select, insert, delete, update.
//            Statement statement = connection.createStatement();
//            ResultSet rs =  statement.executeQuery("SELECT * FROM posts");
//
//            while (rs.next()){
//                Post post = new Post();
//                post.setTitle(rs.getString("title"));
//                post.setBody(rs.getString("body"));
//
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

        return repository.getAllPost();
    }

    public Post getOnePost(){
//        ArrayList<Post> posts = new ArrayList<>();

//        Post post1 = new Post();
//        post1.setTitle("This is my first post");
//        post1.setBody("Post Body");
//        post1.setDate(new Date());
//
//        posts.add(post1);

//        Connection connection = null;
//        try {
//            //Driver name
//            Class.forName("org.postgresql.Driver");
//            //JDBC API for the connection with the DB.
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres","nikhil14");
//            //Statement object to fire the command such as select, insert, delete, update.
//            Statement statement = connection.createStatement();
//            ResultSet rs =  statement.executeQuery("SELECT * FROM posts where id = 4");
//
//            while (rs.next()){
//                Post post = new Post();
//                post.setTitle(rs.getString("title"));
//                post.setBody(rs.getString("body"));
//
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

        return repository.getLatestPost();
    }

    public void createPost (Post newPost){
        newPost.setDate(new Date());
        repository.createPost(newPost);
        System.out.println("New Post:" + newPost);
    }

    public Post getPost(Integer postId) {
        return repository.getPost(postId);
    }

    public void updatedPost(Post updatePost){
        updatePost.setDate(new Date());
        repository.updatedPost(updatePost);
    }

    public void deletePost(Integer postId){
        repository.deletePost(postId);
    }
}
