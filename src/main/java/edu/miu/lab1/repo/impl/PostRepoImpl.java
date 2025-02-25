package edu.miu.lab1.repo.impl;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.repo.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepoImpl implements PostRepo {
    private static List<Post> posts;
    private static int postId = 333;
    static {
        posts = new ArrayList<>();
        Post p1 = new Post(111,"Post title 1","Content Post title 1", "Author1");
        Post p2 = new Post(222,"Post title 2","Content Post title 2", "Author2");
        posts.add(p1);
        posts.add(p2);
    }


    public List<Post> findAll(){
        return posts;
    }

    public void save(Post p) {
        p.setId(postId);
        postId++;
        posts.add(p);
    }


    @Override
    public void delete(int id) {
        var post = posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst().get();
        posts.remove(post);
    }

    @Override
    public void update(int id, Post p) {
        Post postUpdate = getById(id);
        postUpdate.setTitle(p.getTitle());
        postUpdate.setContent(p.getContent());
        postUpdate.setAuthor(p.getAuthor());
    }

    public Post getById(int id) {
        return posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> findPostByAuthor(String author, int exact) {
        if (exact == 1) {
            return posts.stream()
                    .filter(post -> post.getAuthor().equals(author))
                    .collect(Collectors.toList());
        }
        return posts.stream()
                .filter(post -> post.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());

    }
}
