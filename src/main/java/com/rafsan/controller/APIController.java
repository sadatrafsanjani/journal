package com.rafsan.controller;

import com.rafsan.model.Category;
import com.rafsan.model.Post;
import com.rafsan.service.PostService;
import com.rafsan.service.UserService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
@Api(name = "Journal Rest API", description = "Provides basic crud methods", stage = ApiStage.BETA)
public class APIController {

    // http://localhost:8080/journal/api/v1/post.json
    // http://localhost:8080/jsondoc-ui.html

    private PostService postService;
    private UserService userService;

    @Autowired
    public APIController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value="/api", method = RequestMethod.GET)
    @ApiMethod(description = "Get all visible posts")
    public Iterable<Post> posts() throws Exception {

        Iterable<Post> posts = postService.getPosts();

        if(posts == null){
            throw new Exception("Content is unavailable!");
        }

        return posts;
    }

    @RequestMapping(value="/api/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get a specific visible post")
    public Post post(@PathVariable("id") Long id) throws Exception {

        Post post = postService.getPost(id);

        if(post == null){
            throw new Exception("Content is unavailable!");
        }

        return post;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Creates a new post and with default picture upload")
    public Post create(Post post,@RequestParam("file") MultipartFile file,
                       @ModelAttribute Category category,
                       Principal principal){

        String filename = file.getOriginalFilename();
        post.setAuthor(userService.findByUsername(principal.getName()));
        post.setCategory(category);
        post.setPicture(filename);
        Post newPost = postService.savePost(post);

        try {
            String directory = "\\static\\uploads\\";
            String filepath = Paths.get(directory, filename).toString();

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(file.getBytes());
            stream.close();

        } catch (Exception e) {
            throw new RuntimeException("File upload failed!", e);
        }

        return newPost;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @ApiMethod(description = "Updates a specific post")
    public Post update(@ApiPathParam(name = "id") Post post) {

        Post editedPost = postService.updatePost(post);
        return editedPost;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete a post from record permanently")
    public void delete(@ApiPathParam(name = "id") @PathVariable("id") Long id){

        String directory = "\\static\\uploads\\";
        Post post = postService.getPost(id);
        String filename = post.getPicture();
        String filepath = Paths.get(directory, filename).toString();
        new File(filepath).delete();

        postService.deletePost(id);
    }
}
