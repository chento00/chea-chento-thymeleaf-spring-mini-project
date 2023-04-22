package com.example.homework_thymeleaf.user;

import com.example.homework_thymeleaf.category.CategoryController;
import com.example.homework_thymeleaf.post.Post;
import com.example.homework_thymeleaf.post.PostController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServerImp userServerImp;
    private final PostController postController;

    public List<User> findAllUser() {
        return userServerImp.findAllUser();
    }
    public User findUserById(String id){
        return userServerImp.findUserById(id);
    }
    @GetMapping("/user-view")
    String viewUser(Model model) {
        model.addAttribute("users", userServerImp.findAllUser());
        return "page/user";
    }

    @GetMapping("/user-view/{uuid}")
    String viewUserDetail(@PathVariable("uuid") String uuid, Model model) {
        model.addAttribute("user", userServerImp.findUserById(uuid));
        model.addAttribute("posts",postController.findPostByUserId(uuid));
        return "page/view-user-detail";
    }
}
