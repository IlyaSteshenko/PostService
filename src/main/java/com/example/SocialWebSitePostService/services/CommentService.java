package com.example.SocialWebSitePostService.services;

import com.example.SocialWebSitePostService.daos.CommentRepository;
import com.example.SocialWebSitePostService.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public Comment findById(long id) {
        return repository.getReferenceById(id);
    }

    public List<Comment> findAll(long postId) {
        return repository.findByPostId(postId);
    }

    public void create(Comment comment) {
        repository.save(comment);
    }

    public void update(Comment comment) {
        repository.saveAndFlush(comment);
    }

    public void delete(Comment comment) {
        repository.delete(comment);
    }
}
