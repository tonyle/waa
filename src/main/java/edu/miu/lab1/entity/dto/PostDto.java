package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Comment;
import edu.miu.lab1.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private long userId;
    private List<Comment> comments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.userId = post.getUser().getId();
        this.comments = post.getComments();
    }
}
