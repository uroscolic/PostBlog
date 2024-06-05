package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.filters.AuthFilter;
import rs.raf.demo.repositories.comment.CommentRepository;
import rs.raf.demo.repositories.comment.InMemoryCommentRepository;
import rs.raf.demo.repositories.comment.MySqlCommentRepository;
import rs.raf.demo.repositories.post.InMemoryPostRepository;
import rs.raf.demo.repositories.post.MySqlPostRepository;
import rs.raf.demo.repositories.post.PostRepository;
import rs.raf.demo.repositories.user.InMemoryUserRepository;
import rs.raf.demo.repositories.user.UserRepository;
import rs.raf.demo.services.CommentService;
import rs.raf.demo.services.PostService;
import rs.raf.demo.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlPostRepository.class).to(PostRepository.class).in(Singleton.class);

                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);

                this.bind(InMemoryUserRepository.class).to(UserRepository.class).in(Singleton.class);

                this.bindAsContract(PostService.class);

                this.bindAsContract(CommentService.class);

                this.bindAsContract(UserService.class);
            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("rs.raf.demo.resources");
    }
}
