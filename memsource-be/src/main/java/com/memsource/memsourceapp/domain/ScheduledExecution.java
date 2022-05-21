package com.memsource.memsourceapp.domain;

import com.memsource.memsourceapp.enums.ScheduledExecutionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "scheduled_executions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduledExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_started")
    private ZonedDateTime dateStarted;

    @Column(name = "date_finished")
    private ZonedDateTime dateFinished;

    @Column(name = "is_started")
    private Boolean isStarted;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "scheduled_execution_state", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ScheduledExecutionStatus scheduledExecutionStatus;

}
