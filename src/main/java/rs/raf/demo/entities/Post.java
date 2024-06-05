package rs.raf.demo.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Post {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;

    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String author;
    private String date;

    public Post() {
    }

    public Post(String title, String content, String date, String author) {
        this();
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public Post(Integer id, String title, String content, String author, String date) {
        this(title, content, date, author);
        this.id = id;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
