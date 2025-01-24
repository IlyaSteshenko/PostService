package com.example.SocialWebSitePostService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "commenttext")
    @NotEmpty(message = "Text should not be empty")
    private String text;

    @Setter
    @JoinColumn(name = "postid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

    @Setter
    @Getter
    @Column(name = "userid")
    private long userId;

}
