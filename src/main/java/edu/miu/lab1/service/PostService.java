package edu.miu.lab1.service;

import edu.miu.lab1.entity.dto.PostDto;

import java.util.List;

public interface PostService {

    public List<PostDto> findAll();

    PostDto getById(int id);

    void save(PostDto p);

    void delete(int id);

    void update(int id, PostDto p);

    public List<PostDto> findPostByAuthor(String author, int exact);
}
