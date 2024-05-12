package com.userservice.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient //to avoid to save it in the database
    private List<Rating> rating=new ArrayList<>();
}
