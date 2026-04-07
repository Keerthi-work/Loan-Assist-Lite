package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.AuditLogDTO;
import com.example.demo.dto.AuditLogResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.AuditLog;
import com.example.demo.entity.User;
import com.example.demo.repository.AuditLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    private UserDTO convertUserToDTO(User user) {

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());

        // ❌ Never expose password
        dto.setPassword(null);

        return dto;
    }

    private AuditLogResponseDTO convertToResponseDTO(AuditLog entity) {

        AuditLogResponseDTO dto = new AuditLogResponseDTO();
        dto.setAuditId(entity.getAuditId());
        dto.setUser(convertUserToDTO(entity.getUser()));
        dto.setAction(entity.getAction());
        dto.setResource(entity.getResource());
        dto.setTimestamp(entity.getTimestamp());
        dto.setDetails(entity.getDetails());

        return dto;
    }

    private AuditLog convertToEntity(AuditLogDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                    new RuntimeException("User not found with ID: " + dto.getUserId()));

        AuditLog entity = new AuditLog();
        entity.setUser(user);
        entity.setAction(dto.getAction());
        entity.setResource(dto.getResource());
        entity.setDetails(dto.getDetails());
        return entity;
    }

    @Override
    public AuditLogDTO createNewLog(AuditLogDTO dto) {

        AuditLog entity = convertToEntity(dto);
        entity.setTimestamp(LocalDateTime.now());

        AuditLog saved = auditLogRepository.save(entity);

        AuditLogDTO response = new AuditLogDTO();
        response.setAuditId(saved.getAuditId());
        response.setUserId(saved.getUser().getUserId());
        response.setAction(saved.getAction());
        response.setResource(saved.getResource());
        response.setTimestamp(saved.getTimestamp());
        response.setDetails(saved.getDetails());

        return response;
    }

    @Override
    public AuditLogResponseDTO fetchByLogId(Long auditId) {

        AuditLog log = auditLogRepository.findById(auditId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Audit log not found with ID: " + auditId));

        return convertToResponseDTO(log);
    }

    @Override
    public List<AuditLogResponseDTO> fetchAllLogs() {

        List<AuditLog> logs = auditLogRepository.findAll();
        List<AuditLogResponseDTO> responseList = new ArrayList<>();

        for (AuditLog log : logs) {
            responseList.add(convertToResponseDTO(log));
        }
        return responseList;
    }

    @Override
    public List<AuditLogResponseDTO> fetchByUser_UserId(Long userId) {

        List<AuditLog> logs =
                auditLogRepository.findByUser_UserId(userId);

        List<AuditLogResponseDTO> responseList = new ArrayList<>();
        for (AuditLog log : logs) {
            responseList.add(convertToResponseDTO(log));
        }
        return responseList;
    }

    @Override
    public List<AuditLogResponseDTO> fetchByAction(String action) {

        List<AuditLog> logs =
                auditLogRepository.findByAction(action);

        List<AuditLogResponseDTO> responseList = new ArrayList<>();
        for (AuditLog log : logs) {
            responseList.add(convertToResponseDTO(log));
        }
        return responseList;
    }

    @Override
    public AuditLogDTO updateLog(Long auditId, AuditLogDTO dto) {

        AuditLog existing = auditLogRepository.findById(auditId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Audit log not found with ID: " + auditId));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + dto.getUserId()));

        existing.setUser(user);
        existing.setAction(dto.getAction());
        existing.setResource(dto.getResource());
        existing.setDetails(dto.getDetails());

        if (dto.getTimestamp() != null) {
            existing.setTimestamp(dto.getTimestamp());
        }

        AuditLog updated = auditLogRepository.save(existing);

        AuditLogDTO response = new AuditLogDTO();
        response.setAuditId(updated.getAuditId());
        response.setUserId(updated.getUser().getUserId());
        response.setAction(updated.getAction());
        response.setResource(updated.getResource());
        response.setTimestamp(updated.getTimestamp());
        response.setDetails(updated.getDetails());

        return response;
    }
    
    @Override
    public String deleteLog(Long auditId) {

        if (!auditLogRepository.existsById(auditId)) {
            throw new RuntimeException(
                    "Audit log not found with ID: " + auditId);
        }

        auditLogRepository.deleteById(auditId);
        return "Audit log deleted successfully with ID: " + auditId;
    }
}