package com.rafsan.service;

import com.rafsan.model.Comment;
import com.rafsan.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    public Comment saveComment(Comment comment){

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Long id){

        return commentRepository.findByPost(postService.getPost(id));
    }

    public List<Comment> getComments(){

        return commentRepository.findAll();
    }

    public Comment getComment(Long id){

        return commentRepository.findOne(id);
    }

    public void deleteComment(Long id){

        commentRepository.delete(id);
    }
}
