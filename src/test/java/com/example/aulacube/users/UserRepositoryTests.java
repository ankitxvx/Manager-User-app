package com.example.aulacube.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_user(){
        var user = new UserEntity();
            user.setName("John Doe");
            user.setEmail("a@b.com");
            user.setPhone("123456789");

        userRepository.save(user);

    }
}
