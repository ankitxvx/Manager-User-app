package com.example.aulacube.users.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {
    @NonNull
    private String name;
    @NonNull

    private String email;
    @NonNull
    private String phone;



}
