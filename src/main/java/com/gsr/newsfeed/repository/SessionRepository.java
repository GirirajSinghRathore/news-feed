package com.gsr.newsfeed.repository;

import com.gsr.newsfeed.model.Session;
import com.gsr.newsfeed.model.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Optional<Session> findBySessionStatus(SessionStatus sessionStatus);
}
