package com.example.demo.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private String bio;
    private Long id;
    private String avatarUrl;

}
