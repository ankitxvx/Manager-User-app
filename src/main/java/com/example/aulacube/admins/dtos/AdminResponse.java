package com.example.aulacube.admins.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AdminResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
