package com.example.aulacube.admins;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "admin")

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    @NonNull
    private Long id;
    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    @NonNull
    private String email;
    @Column(nullable = false)
    @NonNull
    private String phone;
}
