package rs.raf.demo.repositories.subject;

import rs.raf.demo.entities.Post;

import java.util.List;

public interface PostRepository {
    public Post addPost(Post post);
    public List<Post> allPosts();
    public Post findPost(Integer id);
}
