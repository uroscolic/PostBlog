package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.repositories.subject.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    public CommentService() {
        System.out.println("Creating CommentService");
    }
    @Inject
    private CommentRepository commentRepository;
    public Comment addComment(Comment comment) {
        System.out.println("CS - Adding comment: " + comment);
        return this.commentRepository.addComment(comment);
    }
    public List<Comment> allCommentsForPost(Integer postId) {
        return this.commentRepository.allCommentsForPost(postId);
    }

}
