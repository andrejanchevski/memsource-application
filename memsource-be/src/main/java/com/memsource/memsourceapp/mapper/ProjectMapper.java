package com.memsource.memsourceapp.mapper;

import com.memsource.memsourceapp.domain.response.ProjectResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectMapper {
    ResponseEntity<List<ProjectResponse>> triggerProjectsFetching(String authorizationHeader);
}
