package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.Post;
import rs.raf.demo.repositories.MySqlAbstractRepository;
import rs.raf.demo.repositories.post.PostRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {

    @Override
    public Comment addComment(Comment comment) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (post_id, text, name) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setInt(1, comment.getPostId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setString(3, comment.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allCommentsForPost(Integer postId) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from comments where post_id = " + postId);
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("text"), resultSet.getInt("post_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
