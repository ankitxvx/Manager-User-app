package com.example.aulacube.users;


import com.example.aulacube.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequest request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        //TODO: Set the password here
        return userRepository.save(user);
    }

    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity updateUser(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
//        user.setPassword(password); // Set the password here

        // Save the user after updating the password
        return userRepository.save(user);
    }
    public UserEntity loginUser(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return user;
    }

    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String email) {
            super("User not found with email: " + email);
        }

        public UserNotFoundException(Long userId) {
            super("User not found with id: " + userId);
        }
    }
}
