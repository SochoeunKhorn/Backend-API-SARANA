package com.sochoeun.security.user;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String status;
    private List<String> roles;
}
