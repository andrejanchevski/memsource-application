package com.memsource.memsourceapp.repository;

import com.memsource.memsourceapp.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
