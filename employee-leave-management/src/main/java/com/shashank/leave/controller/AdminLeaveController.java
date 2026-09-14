package com.shashank.leave.controller;

import com.shashank.leave.entity.LeaveRequest;
import com.shashank.leave.entity.LeaveStatus;
import com.shashank.leave.service.LeaveRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/leaves")
public class AdminLeaveController {

    private final LeaveRequestService service;

    public AdminLeaveController(LeaveRequestService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveRequest updateStatus(@PathVariable Long id, @RequestParam LeaveStatus status) {
        return service.updateStatus(id, status);
    }
}
