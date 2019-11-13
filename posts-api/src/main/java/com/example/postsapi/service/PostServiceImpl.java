package com.example.postsapi.service;

import com.example.postsapi.model.Post;
import com.example.postsapi.model.User;
import com.example.postsapi.repository.PostRepository;
import com.example.postsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Post> listPosts() {
        Iterable foundPosts = postRepository.findAll();

//        TODO: iterate over posts and add userbean to each post;
        for (Object post : foundPosts) {
            Post casted = (Post) post;
            Long user_id = casted.getUser_id();

            User user = userRepository.getUserById(user_id);
            casted.setUser(user);
        }

        return foundPosts;
    }

    @Override
    public Post createPost(String userId, Post post) {
        Long user_id = Long.parseLong(userId, 10);
        System.out.println(user_id);

        post.setUser_id(user_id);
        return postRepository.save(post);
    }
}
