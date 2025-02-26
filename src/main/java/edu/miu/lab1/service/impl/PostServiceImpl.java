package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.PostDto;
import edu.miu.lab1.repo.PostRepo;
import edu.miu.lab1.repo.UserRepo;
import edu.miu.lab1.service.PostService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<PostDto> findAll() {
        return postRepo.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(long id, PostDto p) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        post.setAuthor(p.getAuthor());

        postRepo.save(post);
    }

    @Override
    public void save(PostDto p) {
        Post post = new Post();
        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        post.setAuthor(p.getAuthor());

        User user = userRepo.findById(p.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        post.setUser(user);

        postRepo.save(post);
    }

    @Override
    public void delete(long id) {
        if (!postRepo.existsById(id)) {
            throw new EntityNotFoundException("Post not found");
        }
        postRepo.deleteById(id);
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostByAuthor(String author, int exact) {
        List<Post> posts;

        if (exact == 1) {
            posts = postRepo.findByAuthor(author);
        } else {
            posts = postRepo.findByAuthorContaining(author);
        }

        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
