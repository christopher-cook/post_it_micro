package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(String userId, String postId, Comment comment) {
        Long user_id = Long.parseLong(userId);
        Long post_id = Long.parseLong(postId);

        comment.setUser_id(user_id);
        comment.setPost_id(post_id);

        return commentRepository.save(comment);
    }
}
