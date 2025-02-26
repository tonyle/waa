package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private long userId;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.userId = post.getUser().getId();
    }
}
