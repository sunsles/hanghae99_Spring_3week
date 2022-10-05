package com.sparta.week07.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week07.domain.Timestamped;
import com.sparta.week07.dto.PostRequestDto;
import com.sparta.week07.dto.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String comment;

    public Post(String title, String username, String password, String comment) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.comment = comment;
    }
    //
    //dto-그릇 만들기 ,
    //post를 직접 가져다쓰지않고 dto를 통해서 사용하기위해 메소드 생성
    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.comment = requestDto.getComment();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.comment = requestDto.getComment();
    }

    public PostResponseDto postDto() {
        return new PostResponseDto(this.title, this.comment);
    }
}