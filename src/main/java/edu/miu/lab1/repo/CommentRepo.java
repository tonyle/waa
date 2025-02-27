package edu.miu.lab1.repo;

import edu.miu.lab1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
}
