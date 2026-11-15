package com.taskyard.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taskyard.common.exception.ResourceNotFoundException;
import com.taskyard.user.dto.UserResponse;

@Service 
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserById(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id ));
        return userMapper.toResponse(user);
    }
}
