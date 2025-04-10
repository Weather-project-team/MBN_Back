package com.example.mbn.posts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private String tag; // 말머리
    private String platform; // PS, Steam 등

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();


    private int likeCount = 0;
    private int viewCount = 0;
    private int reportCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 글 작성 시간

    // 이 메서드는 'Post' 객체를 생성할 때 작성 시간을 자동으로 설정해줌
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user; // 작성자
}
