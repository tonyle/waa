package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String name;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
    }
}
