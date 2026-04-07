package com.example.demo.dto;

import java.time.LocalDateTime;

public class AuditLogResponseDTO 
{
    private Long auditId;
    private UserDTO user;                
    private String action;
    private String resource;
    private LocalDateTime timestamp;
    private String details;

    public Long getAuditId() {
        return auditId;
    }
    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}