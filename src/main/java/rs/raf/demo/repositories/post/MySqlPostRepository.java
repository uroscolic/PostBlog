package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Post;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostRepository extends MySqlAbstractRepository implements PostRepository {

    @Override
    public Post addPost(Post post) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        System.out.println("Post: " + post);
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO posts (title, content, date, author) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getDate());
            preparedStatement.setString(4, post.getAuthor());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from posts");

            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("author"), resultSet.getString("date")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public Post findPost(Integer id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int postId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                String date = resultSet.getString("date");
                post = new Post(postId, title, content, author, date);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }
}
