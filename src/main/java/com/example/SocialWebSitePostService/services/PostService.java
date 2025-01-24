package com.example.SocialWebSitePostService.services;

import com.example.SocialWebSitePostService.daos.PostRepository;
import com.example.SocialWebSitePostService.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Класс-заместитель класса по работе с БД
// используем этот класс для работы с клиентом
@Component
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post findById(long id) {
        return repository.getReferenceById(id);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public void create(Post post) {
        repository.save(post);
    }

    public void update(Post post) {
        repository.saveAndFlush(post);
    }

    public void delete(Post post) {
        repository.delete(post);
    }

}
