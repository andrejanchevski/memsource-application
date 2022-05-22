package com.memsource.memsourceapp.api;

import com.memsource.memsourceapp.domain.request.ProjectsPagedRequest;
import com.memsource.memsourceapp.domain.response.ProjectPagedResponse;
import com.memsource.memsourceapp.domain.response.ProjectResponse;
import com.memsource.memsourceapp.mapper.ProjectMapper;
import com.memsource.memsourceapp.templates.PagedEntityControllerTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController implements PagedEntityControllerTemplate<ProjectsPagedRequest, ProjectPagedResponse> {

    private final ProjectMapper projectMapper;

    public ProjectController(ProjectMapper projectMapper){
        this.projectMapper = projectMapper;
    }

    @GetMapping("/trigger-fetch-manually")
    ResponseEntity<List<ProjectResponse>> fetchProjectsTriggeredManually(
            @RequestHeader("Authorization") String authorizationHeader){
        return projectMapper.triggerProjectsFetching(authorizationHeader);
    }

    @Override
    @GetMapping("/all")
    public Page<ProjectPagedResponse> findAllPageable(ProjectsPagedRequest projectsPagedRequest) {
        return projectMapper.findAllPageable(projectsPagedRequest);
    }
}
