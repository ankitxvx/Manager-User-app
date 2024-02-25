package com.example.aulacube.admins.dtos;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter
public class LoginAdminRequest {
        @NonNull
        private String email;
        @NonNull
        private String password;
}
