package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {


    @Query("DELETE FROM Comment c WHERE c.post_id = ?1")
    public void deleteCommentsByPostId(Long post_id);

}
