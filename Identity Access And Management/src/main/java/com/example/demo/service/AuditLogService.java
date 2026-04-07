package com.example.demo.service;

	import java.util.List;
	import com.example.demo.dto.AuditLogDTO;
import com.example.demo.dto.AuditLogResponseDTO;

	public interface AuditLogService 
	{
	    public AuditLogDTO createNewLog(AuditLogDTO dto);
	    public AuditLogResponseDTO fetchByLogId(Long auditId);
	    public List<AuditLogResponseDTO> fetchAllLogs();
	    public List<AuditLogResponseDTO> fetchByUser_UserId(Long userId);
	    public List<AuditLogResponseDTO> fetchByAction(String action);
	    public AuditLogDTO updateLog(Long auditId, AuditLogDTO dto);	
	    public String deleteLog(Long auditId);
	}
	