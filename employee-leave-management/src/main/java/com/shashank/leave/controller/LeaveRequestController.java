package com.shashank.leave.controller;

import com.shashank.leave.entity.LeaveRequest;
import com.shashank.leave.entity.LeaveStatus;
import com.shashank.leave.service.LeaveRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest create(@Valid @RequestBody LeaveRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<LeaveRequest> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LeaveRequest findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> findByEmployee(@PathVariable Long employeeId) {
        return service.findByEmployee(employeeId);
    }

    @PatchMapping("/{id}/status")
    public LeaveRequest updateStatus(@PathVariable Long id, @RequestParam LeaveStatus status) {
        return service.updateStatus(id, status);
    }
}
