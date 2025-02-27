package edu.miu.lab1.repo;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByAuthorContaining(String author);
    List<Post> findByAuthor(String author);

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);
    List<Post> findByTitle(String title);

    Optional<Post> findByIdAndUserId(Long postId, Long userId);
}
