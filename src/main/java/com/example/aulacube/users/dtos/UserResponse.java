package com.example.aulacube.users.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter

public class UserResponse{
    private Long id;
    private String name;
    private String email;
    private String phone;

}
