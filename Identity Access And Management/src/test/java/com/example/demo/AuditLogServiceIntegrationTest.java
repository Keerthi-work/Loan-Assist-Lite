package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.AuditLogDTO;
import com.example.demo.service.AuditLogService;

@SpringBootTest
public class AuditLogServiceIntegrationTest {

    @Autowired
    private AuditLogService auditLogService;

    @Test
    void createFetchUpdateDeleteFlow() {
        AuditLogDTO dto = new AuditLogDTO();
        dto.setUserId(42L);
        dto.setAction("TEST_CREATE");
        dto.setResource("LoanApplication");
        dto.setDetails("Integration test create");

        AuditLogDTO created = auditLogService.createNewLog(dto);
        assertThat(created.getAuditId()).isNotNull();
        assertThat(created.getUserId()).isEqualTo(42L);

        AuditLogDTO loaded = auditLogService.fetchByLogId(created.getAuditId());
        assertThat(loaded.getAction()).isEqualTo("TEST_CREATE");

        loaded.setAction("TEST_UPDATE");
        AuditLogDTO updated = auditLogService.updateLog(loaded.getAuditId(), loaded);
        assertThat(updated.getAction()).isEqualTo("TEST_UPDATE");

        List<AuditLogDTO> byUser = auditLogService.fetchByUser_UserId(42L);
        assertThat(byUser).isNotEmpty();

        String deleteMessage = auditLogService.deleteLog(updated.getAuditId());
        assertThat(deleteMessage).contains("deleted successfully");

    }
}
