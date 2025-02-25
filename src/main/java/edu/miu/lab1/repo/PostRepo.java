package edu.miu.lab1.repo;

import edu.miu.lab1.entity.Post;

import java.util.List;

public interface PostRepo {

    public List<Post> findAll();

    public Post getById(int id);

    public void save(Post p);

    public void delete(int id);

    public void update(int id, Post p);

    public List<Post> findPostByAuthor(String author, int exact);
}
