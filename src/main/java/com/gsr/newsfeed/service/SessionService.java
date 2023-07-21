package com.gsr.newsfeed.service;

import com.gsr.newsfeed.model.Session;
import com.gsr.newsfeed.model.SessionStatus;
import com.gsr.newsfeed.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    Session logIn(Long userId){
        Optional<Session> session= sessionRepository.findBySessionStatus(SessionStatus.LOGGED_IN);
        if(session.isPresent()){
            if(session.get().getId()==userId){
                return session.get();
            }
            session.get().setSessionStatus(SessionStatus.LOGGED_OUT);
            session.get().setLoggedOutAt(new Date());
            sessionRepository.save(session.get());
        }
        Session newSession = new Session();
        newSession.setUserId(userId);
        newSession.setSessionStatus(SessionStatus.LOGGED_IN);
        newSession.setLoggedInAt(new Date());
        return sessionRepository.save(newSession);
    }
    public boolean logOut(){
        Optional<Session> session= sessionRepository.findBySessionStatus(SessionStatus.LOGGED_IN);
        if(session.isEmpty()){
            return false;
        }
        session.get().setSessionStatus(SessionStatus.LOGGED_OUT);
        session.get().setLoggedOutAt(new Date());
        sessionRepository.save(session.get());
        return true;
    }

    public Optional<Session> currentLoggedInUser(){
        return sessionRepository.findBySessionStatus(SessionStatus.LOGGED_IN);


    }

}
