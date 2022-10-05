package com.sparta.week07.service;

import com.sparta.week07.dto.PostResponseDto;
import com.sparta.week07.entity.Post;
import com.sparta.week07.dto.PostRequestDto;
import com.sparta.week07.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service //service임을 선언
//Post클래스의 원본이 훼손되지않게 완충제역할인 dto를 통해 사용
//그래서 post클래스를 멤버변수로 가져오지않고 dto를 통해서 보내줌
public class PostService {
    //repository를 필요로함
    private final PostRepository postRepository;

    //    @Autowired
//    public PostService(PostRepository postRepository) {
//
//        this.postRepository = postRepository;
//    }
    @Transactional //sql 작업이 일어나느 곳
    //전체 게시글 목록
    public List<PostResponseDto> readPosts() {
        //모든 포스트 가져오기
        List<Post> posts = this.postRepository.findAllByOrderByModifiedAtDesc();
        //빈 리스트만들기
        List<PostResponseDto> responseDtos = new ArrayList<>();
        //반복문을 통해 생성자를 만들고 list에 추가해줌
        for (int i = 0; i < posts.toArray().length; i++) {
            //빈 temp에 넣어주기
            PostResponseDto temp = new PostResponseDto(posts.get(i));
            responseDtos.add(temp);
        }
        //list반환함
        return responseDtos;
    }

    //게시글 생성
    public Post Post(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    //게시글 수정
    @Transactional //SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        //업데이트 :변경된 requestdto의 데이터를 post원본에 업데이트해준다
        post.update(requestDto);
        return post.getId();
    }

    //삭제
    public Long deletePostId(Long id) {
        postRepository.deleteById(id);
        return id;
    }

    //비밀번호 확인
    public boolean checkPassword(Long id, String password) {
        //reposity에있는 id를 찾아서 post에 넣기
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        //post.getPassword :기존에 있는 패스워드를 가져와서
        String check = post.getPassword();
        //check에 euns가 있으면 true, 없으면 false
        return check.equals(password);
    }
}
