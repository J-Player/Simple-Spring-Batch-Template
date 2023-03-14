package com.example.entity;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class User {

    @Column
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;


}
