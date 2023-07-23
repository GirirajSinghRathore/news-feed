package com.gsr.newsfeed.repository;

import com.gsr.newsfeed.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(value = " select * from post p",nativeQuery = true)
    List<Post> findAllPost();

    @Query(value = "\n" +
            "select p.* , \n" +
            "(p.likes - p.dislikes) AS score,\n" +
            "    COUNT(c.id) AS num_comments\n" +
            "    from post p \n" +
            "left join comment c on p.id = c.on_post_id \n" +
            "\n" +
            "where p.user_id  in (select f.followee  from follow f where f.follower = :id )\n" +
            "\n" +
            "group by p.id\n" +
            "\n" +
            "order by \n" +
            "score desc,\n" +
            "num_comments desc,\n" +
            "p.generated_at desc",nativeQuery = true)
    List<Post> findAllPostsThatUserFollow(@Param("id") Long id);

    @Query(value = "\n" +
            "select p.* , \n" +
            "(p.likes - p.dislikes) AS score,\n" +
            "    COUNT(c.id) AS num_comments\n" +
            "    from post p \n" +
            "left join comment c on p.id = c.on_post_id \n" +
            "\n" +
            "where p.user_id not in (select f.followee  from follow f where f.follower = :id )\n" +
            "\n" +
            "group by p.id\n" +
            "\n" +
            "order by \n" +
            "score desc,\n" +
            "num_comments desc,\n" +
            "p.generated_at desc",nativeQuery = true)
    List<Post> findAllPostsThatUserNotFollow(@Param("id") Long id);
}
