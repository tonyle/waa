package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.Comment;
import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.dto.CommentDto;
import edu.miu.lab1.repo.CommentRepo;
import edu.miu.lab1.repo.PostRepo;
import edu.miu.lab1.repo.UserRepo;
import edu.miu.lab1.service.CommentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private final CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(long PostId, CommentDto dto) {
        Comment comment = new Comment();
        comment.setName(dto.getName());
        Post post = postRepo.findById(PostId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        comment.setPost(post);

        commentRepo.save(comment);
    }
}
