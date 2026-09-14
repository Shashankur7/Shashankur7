package com.shashank.leave.service;

import com.shashank.leave.entity.LeaveRequest;
import com.shashank.leave.entity.LeaveStatus;
import com.shashank.leave.repository.LeaveRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository repository;

    @InjectMocks
    private LeaveRequestService service;

    @Test
    void createShouldSetPendingStatus() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(1L);
        request.setStartDate(LocalDate.of(2026, 10, 1));
        request.setEndDate(LocalDate.of(2026, 10, 3));
        when(repository.save(any(LeaveRequest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LeaveRequest saved = service.create(request);

        assertEquals(LeaveStatus.PENDING, saved.getStatus());
    }

    @Test
    void createShouldRejectInvalidDateRange() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(1L);
        request.setStartDate(LocalDate.of(2026, 10, 5));
        request.setEndDate(LocalDate.of(2026, 10, 3));

        assertThrows(IllegalArgumentException.class, () -> service.create(request));
    }
}
