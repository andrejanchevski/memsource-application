package com.memsource.memsourceapp.repository;

import com.memsource.memsourceapp.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
    Optional<Project> findByExternalId(Long externalId);
}
