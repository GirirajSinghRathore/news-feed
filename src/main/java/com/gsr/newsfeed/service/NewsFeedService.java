package com.gsr.newsfeed.service;

import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.model.Session;
import com.gsr.newsfeed.model.User;
import com.gsr.newsfeed.repository.UserRepository;
import com.gsr.newsfeed.strategy.DefaultNewsFeedStrategy;
import com.gsr.newsfeed.strategy.NewsFeedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsFeedService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsFeedStrategy newsFeedStrategy ;
    public List<Post> getNewsFeed() throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        User user = userRepository.getById(session.get().getUserId());

        return newsFeedStrategy.getNewsFeed(user.getId());
    }
}
