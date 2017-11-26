package com.rafsan.controller;

import com.rafsan.model.*;
import com.rafsan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class PageController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 3;

    private UserService userService;
    private PostService postService;
    private CategoryService categoryService;
    private CommentService commentService;
    private PasswordEncoder passwordEncoder;
    private NotificationService notificationService;

    @Autowired
    public PageController(UserService userService,
                          PostService postService,
                          CategoryService categoryService,
                          CommentService commentService,
                          PasswordEncoder passwordEncoder,
                          NotificationService notificationService) {
        this.userService = userService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.passwordEncoder = passwordEncoder;
        this.notificationService = notificationService;
    }


    @GetMapping("/")
    public String index(ModelMap modelMap,
                        @RequestParam("pageSize") Optional<Integer> pageSize,
                        @RequestParam("page") Optional<Integer> page) throws Exception {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Post> posts = postService.getAllPublishedPosts(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(posts.getTotalPages(), posts.getNumber(), BUTTONS_TO_SHOW);

        modelMap.put("pageName","Home");
        modelMap.put("posts", posts);
        modelMap.put("pager", pager);
        modelMap.put("selectedPageSize", evalPageSize);

        return "index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, ModelMap modelMap) throws Exception {

        Post post = postService.getPost(id);

        if(post == null){
            throw new Exception("Content is unavailable!");
        }
        else{
            modelMap.put("pageName", "Post");
            modelMap.put("comment", new Comment());
            modelMap.put("post", post);
            modelMap.put("categories", categoryService.getCategories());
            modelMap.put("comments", commentService.getCommentsByPost(id));
        }

        return "post";
    }

    @GetMapping("/author/{id}")
    public String author(@PathVariable("id") Long id, ModelMap modelMap,
                         @RequestParam("pageSize") Optional<Integer> pageSize,
                         @RequestParam("page") Optional<Integer> page) throws Exception {

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Post> posts = postService.getPostByAuthor(new PageRequest(evalPage, evalPageSize), userService.getUser(id));
        Pager pager = new Pager(posts.getTotalPages(), posts.getNumber(), BUTTONS_TO_SHOW);

        modelMap.put("pageName", "Articles");
        modelMap.put("posts", posts);
        modelMap.put("pager", pager);
        modelMap.put("id", id);
        modelMap.put("selectedPageSize", evalPageSize);

        return "author";
    }

    @GetMapping("/login")
    public String login(Principal principal, ModelMap modelMap){

        modelMap.put("pageName","Login");

        if ((principal != null) && ((Authentication)principal).isAuthenticated()) {
            return "redirect:/dashboard";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

        if(roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "redirect:/admin/dashboard";
        }
        else if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))){
            return "redirect:/user/profile";
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String register(Principal principal, ModelMap modelMap){

        modelMap.put("pageName", "Register");
        modelMap.put("user", new User());

        if ((principal != null) && ((Authentication)principal).isAuthenticated()) {
            return "redirect:/dashboard";
        }
        else {
            return "register";
        }
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            return "redirect:/register";
        }
        else{

            User check = userService.findByEmail(user.getEmail());

            if(check == null){
                Role role = new Role();
                role.setRole("ROLE_USER");
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(roles);
                userService.saveUser(user);
                //notificationService.sendNotification(user);
            }
            else{
                throw new Exception("User already exist!");
            }

            return "redirect:/login";
        }
    }
}
