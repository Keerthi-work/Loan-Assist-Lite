package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>
{

	public List<AuditLog> findByUser_UserId(Long userId);

	public List<AuditLog> findByAction(String action);
}
