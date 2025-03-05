package edu.miu.lab1.repo;

import edu.miu.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > 1")
    List<User> findUsersWithMultiplePosts();

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > :postCount")
    List<User> findUsersWithMoreThanNPosts(@Param("postCount") int postCount);

    @Query("SELECT DISTINCT u FROM User u JOIN u.posts p WHERE p.title = :title")
    List<User> findUsersByPostTitle(@Param("title") String title);

}
