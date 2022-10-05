package com.sparta.week07.contorller;

import com.sparta.week07.dto.PostResponseDto;
import com.sparta.week07.entity.Post;
import com.sparta.week07.dto.PostRequestDto;
import com.sparta.week07.repository.PostRepository;
import com.sparta.week07.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController //json으로 데이터를 주고받음 선언
public class PostContorller {
    //    private final PostRepository postRepository;
    private final PostService postService;
    private final PostRepository postRepository;
    //
    //전체 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> findPost() {
        return postService.readPosts();
    }

    //게시글 생성
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
//        return postService.createPosts(requestDto);
        Post post = new Post(requestDto); //dto정보를 Post에 담기
        return postRepository.save(post);
    }
    //게시글 조회
    @GetMapping("/api/posts/{id}")
    //한개만 가져옴 :optional
    public Optional<Post> getPosts(@PathVariable Long id) {
        return postRepository.findById(id);

    }
    //비밀번호 확인
    @PostMapping("/api/posts/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        //dto에 있는 패스워드를 postservice로 반환
        return postService.checkPassword(id, requestDto.getPassword());
    }

    //수정
    @PutMapping("/api/posts/{id}")
    //pathvariable :URL 경로에 변수를 넣어주는거
    public Long updatePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto){
        postService.update(id,requestDto);
        return id;
    }
    //삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }

}