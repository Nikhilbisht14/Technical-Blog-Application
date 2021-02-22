package technicalblog.controller;

import jdk.internal.dynalink.linker.LinkerServices;
//import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.stylesheets.LinkStyle;
import technicalblog.model.Category;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPost(Model model){
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @RequestMapping("/posts/newpost")
    public String newPost(){
        return "posts/createpost";
    }

    @RequestMapping(value = "/posts/create" , method = RequestMethod.POST)
    public String createPost(Post newPost, HttpSession session){
        User user = (User) session.getAttribute("loggedUser");
        newPost.setUser(user);

        if (newPost.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(newPost.getSpringBlog());
            newPost.getCategories().add(springBlogCategory);
        }

        if (newPost.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(newPost.getJavaBlog());
            newPost.getCategories().add(javaBlogCategory);
        }

        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name = "postId") Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post",post);
        return "posts/edit";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postId") Integer postId, Post updatedPost, HttpSession session) {
        updatedPost.setId(postId);
        User user = (User) session.getAttribute("loggedUser");
        updatedPost.setUser(user);
        postService.updatedPost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postId") Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
