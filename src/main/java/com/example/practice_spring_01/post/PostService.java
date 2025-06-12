package com.example.practice_spring_01.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService
{
    private final PostRepository postRepository;

    //게시글 등록
    public void create(String title, String content)
    {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }
    //게시글 조회 단건 조회
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
    }
    //전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //게시글 삭제
    public void deleteById(Long id) {
        this.postRepository.deleteById(id);
    }

    //게시글 수정
    public void update(Long id, String title, String content)
    {
        Post post = findById(id);
        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
    }

}
