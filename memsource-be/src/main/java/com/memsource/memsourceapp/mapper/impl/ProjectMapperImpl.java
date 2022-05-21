package com.memsource.memsourceapp.mapper.impl;

import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.response.ProjectResponse;
import com.memsource.memsourceapp.mapper.ProjectMapper;
import com.memsource.memsourceapp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProjectMapperImpl implements ProjectMapper {

    private final ProjectService projectService;
    @Override
    public ResponseEntity<List<ProjectResponse>> triggerProjectsFetching(String authorizationHeader) {
        try {
            List<Project> fetchedNewProjects = projectService.fetchLatestProjects(authorizationHeader);
            return ResponseEntity.ok().body(fetchedNewProjects.stream()
                    .map(it -> ProjectResponse.builder()
                            .projectId(it.getId())
                            .projectName(it.getProjectName())
                            .projectStatus(it.getProjectStatus().name())
                            .build()).collect(Collectors.toList()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
}
