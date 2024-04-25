package rs.raf.demo.repositories.subject;

import rs.raf.demo.entities.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPostRepository implements PostRepository {
    private static List<Post> posts = new CopyOnWriteArrayList<>();

    public InMemoryPostRepository() {
        System.out.println(this);
    }

    @Override
    public synchronized Post addPost(Post post) {
        Integer id = posts.size();
        post.setId(id);
        post.setDate(LocalDate.now().toString().substring(0, 10));
        posts.add(Math.toIntExact(id), post);
        return post;
    }

    @Override
    public List<Post> allPosts() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post findPost(Integer id) {
        return posts.get(id);
    }

}
