package com.memsource.memsourceapp.service;

import com.memsource.memsourceapp.domain.ScheduledExecution;
import com.memsource.memsourceapp.enums.ScheduledExecutionStatus;

import java.time.ZonedDateTime;

public interface ScheduledExecutionService {
    ScheduledExecution save(ZonedDateTime dateStarted,
                            ZonedDateTime dateFinished,
                            Boolean isStarted,
                            Boolean isCompleted,
                            String errorMessage,
                            ScheduledExecutionStatus scheduledExecutionStatus);
}
