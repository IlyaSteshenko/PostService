package com.example.SocialWebSitePostService.controllers;

import com.example.SocialWebSitePostService.daos.PostRepository;
import com.example.SocialWebSitePostService.models.Post;
import com.example.SocialWebSitePostService.services.KafkaProducerService;
import com.example.SocialWebSitePostService.services.PostService;
import jakarta.validation.Valid;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
public class PostCreationController {

    @Autowired
    private PostService postService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    // Тут обрабатываются данные при создании поста
    @GetMapping("/newpost")
    public String showCreationPostPage(Model model) {
        model.addAttribute("post", new Post());
        return "create_post";
    }

    @PostMapping("/newpost")
    public String createPost(
            @ModelAttribute(name = "post")
            @Valid
            Post post,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "create_post";

        post.setCreationDateTime(LocalDateTime.now());
        postService.create(post);

        String message = String.format("Author %d was published a new post: %s\n\nLink to post: http://localhost:8000/post/%d", post.getUserId(), post.getTitle(), post.getId());

        kafkaProducerService.sendMessage(message);

        return "create_post";
    }

}
