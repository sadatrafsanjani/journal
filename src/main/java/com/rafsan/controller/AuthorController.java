package com.rafsan.controller;

import com.rafsan.model.*;
import com.rafsan.service.CategoryService;
import com.rafsan.service.CommentService;
import com.rafsan.service.PostService;
import com.rafsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AuthorController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 6;

    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private CommentService commentService;

    @Autowired
    public AuthorController(UserService userService,
                            PostService postService,
                            CategoryService categoryService,
                            CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @GetMapping("/user/profile")
    public String dashboard(ModelMap modelMap, Principal principal,
                            @RequestParam("pageSize") Optional<Integer> pageSize,
                            @RequestParam("page") Optional<Integer> page) throws Exception {

        String name = principal.getName();
        User user = userService.findByUsername(name);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Post> posts = postService.getPostByAuthor(new PageRequest(evalPage, evalPageSize), userService.getUser(user.getId()));
        Pager pager = new Pager(posts.getTotalPages(), posts.getNumber(), BUTTONS_TO_SHOW);

        modelMap.put("pageName", "Profile");
        modelMap.put("posts", posts);
        modelMap.put("pager", pager);
        modelMap.put("selectedPageSize", evalPageSize);

        return "users/profile";
    }

    @GetMapping("/user/new")
    public String create(ModelMap modelMap){

        modelMap.put("pageName","New");
        modelMap.put("post",new Post());
        modelMap.put("categories",categoryService.getCategories());

        return "users/new";
    }

    @PostMapping("/user/new")
    public String createSubmit(@Valid Post post,
                               BindingResult bindingResult,
                               @RequestParam("file") MultipartFile file,
                               @ModelAttribute Category category,
                               Principal principal){

        String filename = file.getOriginalFilename();

        if(bindingResult.hasErrors() || filename.isEmpty()){
            return "redirect:/user/new";
        }
        else{
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

            return "redirect:/post/" + newPost.getId();
        }
    }

    @GetMapping("/user/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap modelMap){

        modelMap.put("pageName","Edit");
        modelMap.put("post",postService.getPost(id));
        modelMap.put("categories",categoryService.getCategories());

        return "users/edit";
    }

    @PostMapping("/user/edit")
    public String editPost(@Valid Post post,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "redirect:/user/edit/" + post.getId();
        }
        else{
            Post editedPost = postService.updatePost(post);

            return "redirect:/post/" + editedPost.getId();
        }
    }

    @GetMapping("/user/delete/{id}")
    public String dump(@PathVariable Long id){

        postService.deletePost(id);

        return "redirect:/user/success";
    }

    @GetMapping("/user/activate/{id}")
    public String activate(@PathVariable Long id){

        postService.activation(id);

        return "redirect:/user/profile";
    }

    @GetMapping("/user/deactivate/{id}")
    public String deactivate(@PathVariable Long id){

        postService.activation(id);

        return "redirect:/user/profile";
    }

    @GetMapping("/user/success")
    public String success(ModelMap modelMap){

        modelMap.put("pageName","Success");

        return "users/success";
    }

    @PostMapping("/user/comment")
    public String commentSubmit(@Valid Comment comment ,
                               BindingResult bindingResult,
                               Long id,
                               Principal principal){

        if(bindingResult.hasErrors()){
            return "redirect:/post/" + id;
        }
        else{
            String name = principal.getName();
            User user = userService.findByUsername(name);
            Post post = postService.getPost(id);
            comment.setPost(post);
            comment.setUser(user);
            commentService.saveComment(comment);

            return "redirect:/post/" + id;
        }
    }
}
