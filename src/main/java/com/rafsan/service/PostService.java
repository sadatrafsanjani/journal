package com.rafsan.service;

import com.rafsan.model.Category;
import com.rafsan.model.Post;
import com.rafsan.model.User;
import com.rafsan.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> getAllPublishedPosts(Pageable pageable){

        return postRepository.findByVisibilityIsTrueOrderByIdDesc(pageable);
    }

    public Page<Post> getPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    public Iterable<Post> getPosts(){
        return postRepository.findByVisibilityIsTrueOrderByIdDesc();
    }

    public Post getPost(Long id){
        return postRepository.findOne(id);
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public Post updatePost(Post post){

        Category category = post.getCategory();
        String title = post.getTitle();
        String body = post.getBody();
        postRepository.update(category, title, body, post.getId());

        return postRepository.findOne(post.getId());
    }

    public void deletePost(Long id){
        postRepository.delete(id);
    }

    public Page<Post> getPostByAuthor(Pageable pageable, User user){

        return postRepository.findPostByAuthor(pageable, user);
    }

    public void activation(Long id){

        Post post = postRepository.findOne(id);

        if(post.isVisibility()){
            postRepository.activation(false, id);
        }
        else{
            postRepository.activation(true, id);
        }
    }
}
