package com.gsr.newsfeed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Follow extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;
    @ManyToOne
    @JoinColumn(name = "followee")
    private User followee;
}
