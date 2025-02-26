package edu.miu.lab1.repo;

import edu.miu.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAll();

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > 1")
    List<User> findUsersWithMultiplePosts();
}
