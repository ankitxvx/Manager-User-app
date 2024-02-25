package com.example.aulacube.users.dtos;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter
public class LoginUserRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;


}
