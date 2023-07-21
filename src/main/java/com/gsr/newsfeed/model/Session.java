package com.gsr.newsfeed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private Long userId;
    private Date loggedInAt;
    private Date loggedOutAt;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
