package edu.miu.lab1.service;

import edu.miu.lab1.entity.Comment;
import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.UserDto;
import edu.miu.lab1.entity.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id);

    UserResponseDto findByIdDto(long id);

    void deleteById(long id);

    void save(UserDto p);

    List<User> getWithMultiplePosts();

    List<User> getUsersWithMoreThanNPosts(int postCount);

    List<User> getUsersByPostTitle(String title);

    Comment getCommentByUserPostAndCommentId(Long userId, Long postId, Long commentId);
}
