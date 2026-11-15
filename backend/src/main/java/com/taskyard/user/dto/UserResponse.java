package com.taskyard.user.dto;

import java.time.Instant;
import java.util.UUID;

public final class UserResponse {
    private final UUID id;
    private final String email;
    private final String displayName;
    private final Instant createdAt;

    public UserResponse(UUID id, String email, String displayName, Instant createdAt){
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.createdAt = createdAt;
    }

    public UUID getId() {return id;}
    public String getEmail() {return email;}
    public String getDisplayName() {return displayName;}
    public Instant getCreatedAt() {return createdAt;}
}
