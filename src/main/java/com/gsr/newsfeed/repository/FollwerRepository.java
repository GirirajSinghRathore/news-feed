package com.gsr.newsfeed.repository;

import com.gsr.newsfeed.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollwerRepository extends JpaRepository<Follow,Long> {
}
