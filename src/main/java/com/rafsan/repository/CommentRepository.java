package com.rafsan.repository;

import com.rafsan.model.Comment;
import com.rafsan.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post = ?1")
    List<Comment> findByPost(Post post);
}
