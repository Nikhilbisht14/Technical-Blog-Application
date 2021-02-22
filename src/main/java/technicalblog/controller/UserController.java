package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.model.UserProfile;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping("user/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("user/registration")
    public String registration(Model model){
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);

        model.addAttribute("User",user);

        return "user/registration";
    }

    @RequestMapping(value = "user/login" , method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session){
        User existingUser = userService.login(user);
        if (existingUser != null) {
            session.setAttribute("loggedUser", existingUser);
            return "redirect:/posts";
        } else {
            return "user/login";
        }
    }

    @RequestMapping(value = "user/logout", method = RequestMethod.POST)
    public String logout(Model model, HttpSession session){
        session.invalidate();
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "index";
    }

    @RequestMapping(value = "user/registration" , method = RequestMethod.POST)
    public String UserRegistration(User user){
        userService.registerUser(user);
        return "user/login";
    }
}
