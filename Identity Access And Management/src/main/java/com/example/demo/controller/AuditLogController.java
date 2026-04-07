package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuditLogDTO;
import com.example.demo.dto.AuditLogResponseDTO;
import com.example.demo.service.AuditLogService;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    // ✅ CREATE → return created log
    @PostMapping("/add")
    public ResponseEntity<AuditLogResponseDTO> createAuditLog(
            @RequestBody AuditLogDTO dto) {

        AuditLogDTO created = auditLogService.createNewLog(dto);

        // fetch full response with user details
        AuditLogResponseDTO response =
                auditLogService.fetchByLogId(created.getAuditId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ✅ FETCH ALL → return data
    @GetMapping("/all")
    public ResponseEntity<List<AuditLogResponseDTO>> getAllAuditLogs() {
        return ResponseEntity.ok(
                auditLogService.fetchAllLogs());
    }

    // ✅ FETCH BY ID → return data
    @GetMapping("/{id}")
    public ResponseEntity<AuditLogResponseDTO> getAuditLogById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                auditLogService.fetchByLogId(id));
    }

    // ✅ FETCH BY USER → return data
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLogResponseDTO>> getAuditLogsByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                auditLogService.fetchByUser_UserId(userId));
    }

    // ✅ FETCH BY ACTION → return data
    @GetMapping("/action/{action}")
    public ResponseEntity<List<AuditLogResponseDTO>> getAuditLogsByAction(
            @PathVariable String action) {
        return ResponseEntity.ok(
                auditLogService.fetchByAction(action));
    }

    // ✅ UPDATE → return updated data
    @PutMapping("/{id}")
    public ResponseEntity<AuditLogResponseDTO> updateAuditLog(
            @PathVariable Long id,
            @RequestBody AuditLogDTO dto) {

        auditLogService.updateLog(id, dto);

        AuditLogResponseDTO response =
                auditLogService.fetchByLogId(id);

        return ResponseEntity.ok(response);
    }

    // ✅ DELETE → return message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable Long id) {
        return ResponseEntity.ok(
                auditLogService.deleteLog(id));
    }
}
