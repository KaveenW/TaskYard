package com.taskyard.user;

import org.springframework.stereotype.Component;

import com.taskyard.user.dto.UserResponse;

@Component 
public class UserMapper {
    public UserResponse toResponse(User user){
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getDisplayName(),
            user.getCreatedAt()
        );
    }
}
