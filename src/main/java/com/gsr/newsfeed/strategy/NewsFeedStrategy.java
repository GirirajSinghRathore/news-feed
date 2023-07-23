package com.gsr.newsfeed.strategy;

import com.gsr.newsfeed.model.Post;

import java.util.List;

public interface NewsFeedStrategy {
    List<Post> getNewsFeed(Long userId);
}
