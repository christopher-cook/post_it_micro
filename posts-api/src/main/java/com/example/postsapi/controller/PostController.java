package com.example.postsapi.controller;

import com.example.postsapi.model.Comment;
import com.example.postsapi.model.Post;
import com.example.postsapi.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/list")
    public Iterable<Post> listAll(){
        return postService.listPosts();
    }

    @PostMapping("/")
    public Post createPost(@RequestHeader("userId") String userId, @RequestBody Post post) {
        System.out.println(userId);
        return postService.createPost(userId, post);
    }

    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return postService.getCommentsByPostId(postId);
    }


}
