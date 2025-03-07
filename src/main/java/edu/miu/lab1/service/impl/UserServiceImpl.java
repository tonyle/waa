package edu.miu.lab1.service.impl;

import edu.miu.lab1.aop.ExecutionTime;
import edu.miu.lab1.entity.Comment;
import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.Role;
import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.UserDto;
import edu.miu.lab1.entity.dto.UserResponseDto;
import edu.miu.lab1.repo.CommentRepo;
import edu.miu.lab1.repo.PostRepo;
import edu.miu.lab1.repo.RoleRepo;
import edu.miu.lab1.repo.UserRepo;
import edu.miu.lab1.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private final RoleRepo roleRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserResponseDto findByIdDto(long id){
        return modelMapper.map(userRepo.findById(id), UserResponseDto.class);
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void save(UserDto dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setPosts(dto.getPosts());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        List<Role> roles = roleRepo.findAllById(dto.getRoleIds());
        u.setRoles(roles);
        userRepo.save(u);
    }

    @Override
    @ExecutionTime
    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> getWithMultiplePosts() {
        return userRepo.findUsersWithMultiplePosts();
    }

    @Override
    public List<User> getUsersWithMoreThanNPosts(int postCount) {
        return userRepo.findUsersWithMoreThanNPosts(postCount);
    }

    @Override
    public List<User> getUsersByPostTitle(String title) {
        return userRepo.findUsersByPostTitle(title);
    }

    @Override
    public Comment getCommentByUserPostAndCommentId(Long userId, Long postId, Long commentId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = postRepo.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        return commentRepo.findByIdAndPostId(commentId, post.getId())
                .orElseThrow(() -> new EntityNotFoundException("Comment with ID " + commentId + " not found for post " + postId));
    }


}
