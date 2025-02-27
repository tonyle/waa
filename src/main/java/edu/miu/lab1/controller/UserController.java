package edu.miu.lab1.controller;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;

import edu.miu.lab1.entity.dto.PostDto;
import edu.miu.lab1.entity.dto.UserDto;
import edu.miu.lab1.repo.PostRepo;
import edu.miu.lab1.repo.UserRepo;
import edu.miu.lab1.service.PostService;
import edu.miu.lab1.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final PostService postService;

    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
       userService.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody UserDto p) {
        userService.save(p);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> getPostsByUserId(@PathVariable long id) {
        User user = userService.findById(id).orElseThrow(EntityNotFoundException::new);
        return postService.getByUser(user);
    }

    @GetMapping("/multiple-posts")
    public List<User> getUsersWithMultiplePosts() {
        return userService.getWithMultiplePosts();
    }

}
