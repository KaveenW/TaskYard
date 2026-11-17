package com.taskyard.workspace;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class WorkspaceMemberId implements Serializable {
    // fields, constructor, equals, hashCode

    private UUID workspaceId;

    private UUID userId;

    public WorkspaceMemberId(UUID workspaceId, UUID userId){
        this.workspaceId = workspaceId;
        this.userId = userId;
    }

    public UUID getWorkspaceId(){return workspaceId;}
    public void setWorkspaceId(UUID workspaceId){this.workspaceId = workspaceId;}

    public UUID getUserId(){return userId;}
    public void setUserId(UUID userId){this.userId = userId;}

    @Override
    public boolean equals(Object o) {
        // 1. "Are you comparing the exact same object in your hand to itself? 
        // If yes, obviously they are equal."
        if (this == o) return true;

        // 2. "Is the other thing even an ID card? Or is it a banana or null? 
        // If it's not an ID card, stop right there, they are NOT equal."
        if (o == null || getClass() != o.getClass()) return false;

        // 3. "Okay, open up the other object and look at its contents."
        WorkspaceMemberId that = (WorkspaceMemberId) o;

        // 4. "Do the workspace IDs match AND do the user IDs match? 
        // If both match, shout YES, they are the same identity!"
        return Objects.equals(workspaceId, that.workspaceId) && 
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workspaceId, userId);
    }
}
