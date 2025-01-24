package com.example.SocialWebSitePostService.controllers;

import com.example.SocialWebSitePostService.daos.CommentRepository;
import com.example.SocialWebSitePostService.daos.PostRepository;
import com.example.SocialWebSitePostService.models.Comment;
import com.example.SocialWebSitePostService.models.Post;
import com.example.SocialWebSitePostService.services.CommentService;
import com.example.SocialWebSitePostService.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/home")
    public String mainPage(Model m) {
        m.addAttribute("posts", postService.findAll());
        return "home_page";
    }

    @GetMapping("/post/{id}")
    public String showFullPost(
            Model m,
            @PathVariable(name = "id")
            long id
    ) {
        Post fullPost = postService.findById(id);
        List<Comment> comments = commentService.findAll(id);

        m.addAttribute("fullPost", fullPost);
        m.addAttribute("creationDate", fullPost.getCreationDateTime().toLocalDate());
        m.addAttribute("creationTime", fullPost.getCreationDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        m.addAttribute("comment", new Comment());
        m.addAttribute("comments", comments);

        return "full_post";
    }

    @PostMapping("/post/{id}")
    public String writeComment(
            @ModelAttribute(name = "comment")
            @Valid
            Comment comment,
            BindingResult b,
            @PathVariable(name = "id")
            long postId
    ) {
        if (b.hasErrors()) return "redirect:/post/" + postId;

        comment.setPost(postService.findById(postId));
        commentService.create(comment);
        return "redirect:/post/" + postId;
    }

}
