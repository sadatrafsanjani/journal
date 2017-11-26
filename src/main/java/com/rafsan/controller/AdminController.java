package com.rafsan.controller;

import com.rafsan.model.Pager;
import com.rafsan.model.Post;
import com.rafsan.model.User;
import com.rafsan.service.PostService;
import com.rafsan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 6;

    private PostService postService;
    private UserService userService;

    @Autowired
    public AdminController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(ModelMap modelMap){

        modelMap.put("pageName","Dashboard");

        return "admin/dashboard";
    }

    @GetMapping("/admin/articles")
    public String articles(ModelMap modelMap,
                           @RequestParam("pageSize") Optional<Integer> pageSize,
                           @RequestParam("page") Optional<Integer> page){

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Post> posts = postService.getPosts(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(posts.getTotalPages(), posts.getNumber(), BUTTONS_TO_SHOW);

        modelMap.put("pageName", "Articles");
        modelMap.put("posts", posts);
        modelMap.put("pager", pager);
        modelMap.put("selectedPageSize", evalPageSize);

        return "admin/articles";
    }

    @GetMapping("/admin/users")
    public String users(ModelMap modelMap,
                        @RequestParam("pageSize") Optional<Integer> pageSize,
                        @RequestParam("page") Optional<Integer> page){

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<User> users = userService.getUsers(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        modelMap.put("pageName","Users");
        modelMap.put("users",users);
        modelMap.put("pager", pager);
        modelMap.put("selectedPageSize", evalPageSize);

        return "admin/users";
    }

    @GetMapping("/admin/remove/{id}")
    public String remove(@PathVariable Long id){

        userService.deleteUser(id);

        return "redirect:/admin/success";
    }

    @GetMapping("/admin/delete/{id}")
    public String dump(@PathVariable Long id){

        String directory = "\\static\\uploads\\";
        Post post = postService.getPost(id);
        String filename = post.getPicture();
        String filepath = Paths.get(directory, filename).toString();
        new File(filepath).delete();
        postService.deletePost(id);

        return "redirect:/admin/success";
    }

    @GetMapping("/admin/activate/{id}")
    public String activate(@PathVariable Long id){

        postService.activation(id);

        return "redirect:/admin/success";
    }

    @GetMapping("/admin/deactivate/{id}")
    public String deactivate(@PathVariable Long id){

        postService.activation(id);

        return "redirect:/admin/success";
    }

    @GetMapping("/admin/success")
    public String success(ModelMap modelMap){

        modelMap.put("pageName","Successful");

        return "admin/success";
    }

    @GetMapping("/admin/settings")
    public String settings(ModelMap modelMap){

        modelMap.put("pageName","Settings");

        return "admin/settings";
    }

    @GetMapping("/admin/details/{id}")
    public String mail(@PathVariable("id") Long id, ModelMap modelMap) throws Exception {

        User user = userService.getUser(id);

        if(user == null){
            throw new Exception("Content is unavailable!");
        }
        else{
            modelMap.put("pageName","Details");
            modelMap.put("user", user);
        }

        return "admin/details";
    }
}
