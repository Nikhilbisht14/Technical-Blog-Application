package technicalblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.util.List;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String getAllPosts(Model model) {

        List<Post> posts = postService.getAllPost();

        model.addAttribute("posts", posts);

        return "index";
    }
}
