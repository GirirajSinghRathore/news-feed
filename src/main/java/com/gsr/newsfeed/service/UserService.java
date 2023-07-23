package com.gsr.newsfeed.service;

import com.gsr.newsfeed.model.Follow;
import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.model.Session;
import com.gsr.newsfeed.model.User;
import com.gsr.newsfeed.repository.FollwerRepository;
import com.gsr.newsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollwerRepository follwerRepository;
    @Autowired
    private SessionService sessionService;
    public User signUp(String name,String email,String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new Exception("User Already Exist");
        }
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        u.setPassword(password);
        return userRepository.save(u);
    }
    public User logIn(String email,String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new Exception("Incorrect Email");
        }
        boolean success = password.equals(user.get().getPassword());
        if(success){
            //update session
            Session session = sessionService.logIn(user.get().getId());
            System.out.println("Logged In At "+ session.getLoggedInAt());
            return user.get();
        }else {
            throw new Exception("Wrong Password");
        }
    }
    public Follow follow(Long id) throws Exception {
        Optional<Session> session = sessionService.currentLoggedInUser();
        if(session.isEmpty()){
            throw new Exception("No Logged In User Found");
        }
        User follwer = userRepository.findById(session.get().getUserId()).get();
        Optional<User> follwes = userRepository.findById(id);
        if(follwes.isEmpty()){
            throw new Exception("Please enter valid Id");
        }
//        Set<User> l = userRepository.getById(session.get().getUserId()).getFollows();
//        follwer.getFollows().
//                add(follwes);
//        return userRepository.save(follwer);
        Follow follow = new Follow();
        follow.setFollower(follwer);
        follow.setFollowee(follwes.get());
        follwerRepository.save(follow);
        return follow;
    }
}
