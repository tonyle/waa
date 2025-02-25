package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Post;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
}
