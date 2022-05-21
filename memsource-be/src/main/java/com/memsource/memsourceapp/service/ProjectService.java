package com.memsource.memsourceapp.service;

import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Void> fetchLatestProjects();
}
