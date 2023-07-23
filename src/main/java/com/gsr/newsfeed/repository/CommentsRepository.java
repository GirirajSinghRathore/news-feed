package com.gsr.newsfeed.repository;

import com.gsr.newsfeed.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Long> {
    @Query(value="SELECT c from Comment c where c.onPost.id = :id and c.onComment.id = null")
    List<Comment> findAllCommentsByPostId(@Param("id") Long id);

    @Query(value="SELECT c from Comment c where c.onComment.id = :id")
    List<Comment> findAllCommentsByCommentId(@Param("id") Long id);
}
