package com.gsr.newsfeed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment extends BaseModel{
    private String content;
    @ManyToOne
    private User postedBy;
    @ManyToOne
    private Post onPost;
    @ManyToOne
    private Comment onComment;
    @OneToMany(mappedBy = "onComment")
    private List<Comment> replies;
    @CreatedDate
    private Date generated_at;
    private int likes;
    private int dislikes;

}
