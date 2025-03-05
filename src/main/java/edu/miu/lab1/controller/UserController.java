package edu.miu.lab1.controller;

import edu.miu.lab1.entity.Comment;
import edu.miu.lab1.entity.User;
import edu.miu.lab1.entity.dto.PostDto;
import edu.miu.lab1.entity.dto.UserDto;
import edu.miu.lab1.entity.dto.UserResponseDto;
import edu.miu.lab1.service.CommentService;
import edu.miu.lab1.service.PostService;
import edu.miu.lab1.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id){
        return userService.findById(id);
//        return  modelMapper.map(userService.findById(id), UserResponseDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
       userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
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

    @GetMapping("/greater-than/{postCount}")
    public List<User> findByPostsSizeGreaterThan(@PathVariable int postCount) {
        return userService.getUsersWithMoreThanNPosts(postCount);
    }

    @GetMapping("/by-post-title")
    public List<User> findByPostsSizeGreaterThan(@RequestParam String title) {
        return userService.getUsersByPostTitle(title);
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public Optional<Comment> getCommentByUserAndPost(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @PathVariable Long commentId) {

        return Optional.ofNullable(userService.getCommentByUserPostAndCommentId(userId, postId, commentId));
    }
}
