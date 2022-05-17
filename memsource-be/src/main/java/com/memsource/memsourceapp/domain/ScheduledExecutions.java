package com.memsource.memsourceapp.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "scheduled_executions")
public class ScheduledExecutions {
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

}
