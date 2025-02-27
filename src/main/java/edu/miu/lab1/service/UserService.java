package edu.miu.lab1.service;

import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id);

    UserDto findByIdDto(long id);

    void deleteById(long id);

    void save(UserDto p);

    List<User> getWithMultiplePosts();
}
