package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.dto.PostDto;
import edu.miu.lab1.helper.ListMapper;
import edu.miu.lab1.repo.PostRepo;
import edu.miu.lab1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public List<PostDto> findAll() {
        return (List<PostDto>) listMapper.mapList(postRepo.findAll(), new PostDto());
    }


    public PostDto getById(int id) {
        Post post = postRepo.getById(id);
        if (post == null) {
            return null;
        }
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void save(PostDto p) {
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void delete(int id) {
        postRepo.delete(id);
    }

    @Override
    public void update(int id,  PostDto p) {
        postRepo.update(id, modelMapper.map(p, Post.class));
    }

    @Override
    public List<PostDto> findPostByAuthor(String author, int exact) {
        return (List<PostDto>) listMapper.mapList(postRepo.findPostByAuthor(author, exact),new PostDto());
    }
}
