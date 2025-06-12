package com.example.practice_spring_01.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController
{
    private final PostService postService;

    @GetMapping
    public List<Post> list()
    {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public void create(@RequestParam String title, @RequestParam String content) {
        postService.create(title, content);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id)
    {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestParam String title, @RequestParam String content)
    {
        postService.update(id, title, content);
    }

}
