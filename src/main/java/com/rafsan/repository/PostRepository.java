package com.rafsan.repository;

import com.rafsan.model.Category;
import com.rafsan.model.Post;
import com.rafsan.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Page findPostByAuthor(Pageable pageable,User user);

    Page findByVisibilityIsTrueOrderByIdDesc(Pageable pageable);

    Iterable<Post> findByVisibilityIsTrueOrderByIdDesc();

    @Modifying
    @Query("update Post p set p.visibility = ?1 where p.id = ?2")
    void activation(boolean status, Long id);

    @Modifying
    @Query("update Post p set " +
            "p.category = ?1, " +
            "p.body = ?2, " +
            "p.title = ?3 where p.id = ?4")
    void update(Category category, String title, String body, Long id);
}
