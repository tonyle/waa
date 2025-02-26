package edu.miu.lab1.repo;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByAuthorContaining(String author);
    List<Post> findByAuthor(String author);

    List<Post> findByUser(User user);

}
