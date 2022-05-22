package com.memsource.memsourceapp.mapper;

import com.memsource.memsourceapp.domain.request.ProjectsPagedRequest;
import com.memsource.memsourceapp.domain.response.ProjectPagedResponse;
import com.memsource.memsourceapp.domain.response.ProjectResponse;
import com.memsource.memsourceapp.templates.PagedEntityMapperTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectMapper extends PagedEntityMapperTemplate<ProjectsPagedRequest, ProjectPagedResponse> {
    ResponseEntity<List<ProjectResponse>> triggerProjectsFetching(String authorizationHeader);
}
