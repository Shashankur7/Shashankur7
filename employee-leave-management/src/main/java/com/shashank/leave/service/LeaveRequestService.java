package com.shashank.leave.service;

import com.shashank.leave.entity.LeaveRequest;
import com.shashank.leave.entity.LeaveStatus;
import com.shashank.leave.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestService(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    public LeaveRequest create(LeaveRequest request) {
        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        request.setStatus(LeaveStatus.PENDING);
        return repository.save(request);
    }

    public List<LeaveRequest> findAll() {
        return repository.findAll();
    }

    public List<LeaveRequest> findByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public LeaveRequest findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leave request not found: " + id));
    }

    public LeaveRequest updateStatus(Long id, LeaveStatus status) {
        LeaveRequest request = findById(id);
        request.setStatus(status);
        return repository.save(request);
    }
}
