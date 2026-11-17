package com.taskyard.workspace;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name="workspace")
public class Workspace {
    
    @Id 
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(name="created_at", nullable=false)
    private Instant createdAt;

    public Workspace(UUID id, String name, String description, Instant createdAt){
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public UUID getId(){return id;}
    public void setId(UUID id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public Instant getCreatedAt(){return createdAt;}
    public void setCreatedAt(Instant createdAt){this.createdAt = createdAt;}
}
