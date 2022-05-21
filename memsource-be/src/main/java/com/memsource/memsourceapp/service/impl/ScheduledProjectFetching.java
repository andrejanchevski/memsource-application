package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.enums.ScheduledExecutionStatus;
import com.memsource.memsourceapp.service.ProjectService;
import com.memsource.memsourceapp.service.ScheduledExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.ZonedDateTime;

@Service
@EnableAsync
@RequiredArgsConstructor
public class ScheduledProjectFetching {

    private final ProjectService projectService;
    private final ScheduledExecutionService scheduledExecutionService;

    @Async
    @Scheduled(cron = "${scheduled.cron_expression}")
    public void scheduledFetchingOfProjectsFromApiClient() {
        ZonedDateTime startedExecutionDate = ZonedDateTime.now();
        scheduledExecutionService.save(startedExecutionDate, null, true,
                false, null, ScheduledExecutionStatus.STARTED);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("memsource_application_token.txt"));
            projectService.fetchLatestProjects(reader.readLine().trim());
            scheduledExecutionService.save(startedExecutionDate, ZonedDateTime.now(), true, true,
                    null, ScheduledExecutionStatus.FINISHED);
        } catch (Exception e) {
            scheduledExecutionService.save(startedExecutionDate, ZonedDateTime.now(), true,
                    true, e.getMessage(), ScheduledExecutionStatus.ERROR);
        }

    }
}
