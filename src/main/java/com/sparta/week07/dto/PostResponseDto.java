package com.sparta.week07.dto;

import com.sparta.week07.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//부분 게시글 조회
@NoArgsConstructor
@Getter
@Setter //값 할당
public class PostResponseDto{
    private String title;
    private String username;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//

    public PostResponseDto(String title, String username) {
        this.title = title;
        this.username = username;
    }
    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.createdAt= post.getCreatedAt();
        this.modifiedAt= post.getModifiedAt();
    }

}