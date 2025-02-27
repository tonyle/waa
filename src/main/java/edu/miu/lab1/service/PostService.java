package edu.miu.lab1.service;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    void update(long id, PostDto p);

    void save(PostDto p);

    void delete(long id);

    PostDto getById(long id);
    public List<PostDto> findPostByAuthor(String author, int exact);

    List<PostDto> getByUser(User user);
}
