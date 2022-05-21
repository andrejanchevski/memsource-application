package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.domain.ScheduledExecution;
import com.memsource.memsourceapp.enums.ScheduledExecutionStatus;
import com.memsource.memsourceapp.repository.ScheduledExecutionRepository;
import com.memsource.memsourceapp.service.ScheduledExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ScheduledExecutionServiceImpl implements ScheduledExecutionService {

    private final ScheduledExecutionRepository scheduledExecutionRepository;
    @Override
    public ScheduledExecution save(
            ZonedDateTime dateStarted,
            ZonedDateTime dateFinished,
            Boolean isStarted, Boolean isCompleted, String errorMessage,
            ScheduledExecutionStatus scheduledExecutionStatus) {
        return scheduledExecutionRepository.save(ScheduledExecution.builder()
                        .dateStarted(dateStarted)
                        .dateFinished(dateFinished)
                        .isStarted(isStarted)
                        .isCompleted(isCompleted)
                        .errorMessage(errorMessage)
                        .scheduledExecutionStatus(scheduledExecutionStatus)
                .build());
    }
}
