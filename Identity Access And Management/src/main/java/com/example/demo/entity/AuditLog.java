package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="AuditLog")
public class AuditLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long auditId;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private String action;
  private String resource;
  private LocalDateTime timestamp;

  private String details;
  
  public AuditLog() {}

  public AuditLog(Long auditId, User user, String action, String resource, LocalDateTime timestamp, String details) {
	super();
	this.auditId = auditId;
	this.user = user;
	this.action = action;
	this.resource = resource;
	this.timestamp = timestamp;
	this.details = details;
  }

  public Long getAuditId() {
	return auditId;
  }

  public void setAuditId(Long auditId) {
	this.auditId = auditId;
  }

  public User getUser() {
	return user;
  }

  public void setUser(User user) {
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

  @Override
  public String toString() {
	return "AuditLog [auditId=" + auditId + ", user=" + user + ", action=" + action + ", resource=" + resource
			+ ", timestamp=" + timestamp + ", details=" + details + "]";
  }
  
  
  
  
}

