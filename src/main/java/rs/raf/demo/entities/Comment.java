package rs.raf.demo.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class Comment {


    private Integer id;
    private Integer postId;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String name;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String text;
    public Comment() {
    }
    public Comment(String name, String text) {
        this();
        this.name = name;
        this.text = text;
    }
    public Comment(Integer id, String name, String text) {
        this(name, text);
        this.id = id;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
