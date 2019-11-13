package com.example.postsapi.service;

import com.example.postsapi.model.Post;

public interface PostService {
    public Iterable<Post> listPosts();

    public Post createPost(String userId, Post post);

}

