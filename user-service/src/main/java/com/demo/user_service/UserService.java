package com.demo.user_service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserRequest request) {
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .build();
        User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    public UserResponse getUserById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow();
        return UserResponse.from(findUser);
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList();
    }
}
