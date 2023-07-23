package com.gsr.newsfeed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")

public class User extends BaseModel {

    private String name;

    private String email;
    private String password;
//    @ManyToMany
//    private Set<User> follows;
//    @ManyToMany(mappedBy="follows")
//    private Set<User> followers;
    @OneToMany
    private List<Post> posts;
    @OneToMany
    private List<Comment> comments;
}