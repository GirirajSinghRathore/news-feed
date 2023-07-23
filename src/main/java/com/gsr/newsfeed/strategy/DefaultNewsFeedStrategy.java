package com.gsr.newsfeed.strategy;

import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.repository.PostRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DefaultNewsFeedStrategy implements NewsFeedStrategy{
    @Autowired
    private PostRepository postRepository ;
    /**
     * @param userId
     * @return
     */
    @Override
    public List<Post> getNewsFeed(Long userId) {
        List<Post> list = new ArrayList<>();
         list.addAll(postRepository.findAllPostsThatUserFollow(userId));
        list.addAll(postRepository.findAllPostsThatUserNotFollow(userId));
        return list;
    }
}
