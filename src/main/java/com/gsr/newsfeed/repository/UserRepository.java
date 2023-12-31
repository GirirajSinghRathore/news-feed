package com.gsr.newsfeed.repository;

import com.gsr.newsfeed.model.Post;
import com.gsr.newsfeed.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User,Long> {

    Optional<User> findByEmail(String email);
    @Query(value = " select * from users u where u.id= :id fetch first row only",nativeQuery = true)
    Optional<User> findByIds(@Param("id") Long id);

}
