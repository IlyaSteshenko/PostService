package com.example.SocialWebSitePostService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Post {

    // Id поста
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    // Название поста
    @Setter
    @Getter
    @Column(name = "articletitle")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 50, message = "Enter correct title (minimum 2 symbols, maximum 50 symbols)")
    private String title;

    // Текст поста
    @Setter
    @Getter
    @Column(name = "articletext")
    @NotEmpty(message = "Text should not be empty")
    private String text;

    // Дата и время создания поста
    @Setter
    @Getter
    @Column(name = "creationdatetime")
    @Basic
    private LocalDateTime creationDateTime;

    // Id пользователя, создавшего пост
    @Setter
    @Getter
    @Column(name = "userid")
    private long userId;

}
