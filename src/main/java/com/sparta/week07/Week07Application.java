package com.sparta.week07;

import com.sparta.week07.entity.Post;
import com.sparta.week07.repository.PostRepository;
import com.sparta.week07.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week07Application {

    public static void main(String[] args) {
        SpringApplication.run(Week07Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(PostRepository postRepository, PostService postService) {
        return (args) -> {
//            postRepository.save(new Post("타이틀1", "김은선","1234","잘되나"));
//            postRepository.save(new Post("타이틀2", "김은선2","2345","잘되나 22"));
//            //locallhost:8080/api/posts에 값들어가는것 확인
//            System.out.println("데이터 인쇄");
//            List<Post> postList = postRepository.findAll();
//            for (int i=0; i<postList.size(); i++) {
//                Post post = postList.get(i);
//                System.out.println(post.getId());
//                System.out.println(post.getTitle());
//                System.out.println(post.getPassword());
//                System.out.println(post.getUsername());
//                System.out.println(post.getComment());


        };
    }
}