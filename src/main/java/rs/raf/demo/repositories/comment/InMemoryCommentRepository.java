package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.comment.CommentRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class InMemoryCommentRepository implements CommentRepository {
    private static List<Comment> comments = new CopyOnWriteArrayList<>();
    public InMemoryCommentRepository() {
        System.out.println("Creating InMemoryCommentRepository");
    }
    @Override
    public Comment addComment(Comment comment) {
        Integer id = comments.size();
        comment.setId(id);
        comments.add(Math.toIntExact(id), comment);
        System.out.println("Comment added: " + comment);
        return comment;
    }

    @Override
    public List<Comment> allCommentsForPost(Integer postId) {

        return comments.stream().filter(comment -> comment.getPostId().equals(postId)).collect(Collectors.toList());
    }
}
