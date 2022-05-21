package com.memsource.memsourceapp.repository;

import com.memsource.memsourceapp.domain.ScheduledExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledExecutionRepository extends JpaRepository<ScheduledExecution, Long> {
}
