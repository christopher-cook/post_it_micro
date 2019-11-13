package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable<Post> listPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(String userId, Post post) {
        Long user_id = Long.parseLong(userId, 10);
        System.out.println(user_id);

        post.setUser_id(user_id);
        return postRepository.save(post);
    }
}
