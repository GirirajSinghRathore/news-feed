package com.gsr.newsfeed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Post extends BaseModel{
    private String content;
    @ManyToOne
    private User user;
    private int likes;
    private int dislikes;
    @CreatedDate
    private Date generated_at;
    @OneToMany
    private List<Comment> comments;

}
