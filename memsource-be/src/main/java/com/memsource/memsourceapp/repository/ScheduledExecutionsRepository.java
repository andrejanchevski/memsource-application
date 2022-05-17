package com.memsource.memsourceapp.repository;

import com.memsource.memsourceapp.domain.ScheduledExecutions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledExecutionsRepository extends JpaRepository<ScheduledExecutions, Long> {
}
