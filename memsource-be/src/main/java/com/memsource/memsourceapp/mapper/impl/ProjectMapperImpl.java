package com.memsource.memsourceapp.mapper.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.TargetLanguage;
import com.memsource.memsourceapp.domain.dto.PagedProjectsRequestDTO;
import com.memsource.memsourceapp.domain.request.ProjectsPagedRequest;
import com.memsource.memsourceapp.domain.response.ProjectPagedResponse;
import com.memsource.memsourceapp.domain.response.ProjectResponse;
import com.memsource.memsourceapp.mapper.ProjectMapper;
import com.memsource.memsourceapp.service.ProjectService;
import com.memsource.memsourceapp.util.JsonWebTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProjectMapperImpl implements ProjectMapper {

    private final JsonWebTokenUtils jsonWebTokenUtils;

    private final ProjectService projectService;

    @Override
    public ResponseEntity<List<ProjectResponse>> triggerProjectsFetching(String authorizationHeader) {
        try {
            DecodedJWT decodedJWT = jsonWebTokenUtils.decodedJWT(authorizationHeader);
            String apiClientAuthenticationToken = decodedJWT.getClaim("memsourceApiToken").asString();
            List<Project> fetchedNewProjects = projectService.fetchLatestProjects(apiClientAuthenticationToken);
            return ResponseEntity.ok().body(fetchedNewProjects.stream()
                    .map(it -> ProjectResponse.builder()
                            .projectId(it.getId())
                            .projectName(it.getProjectName())
                            .projectStatus(it.getProjectStatus().name())
                            .build()).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @Override
    public Page<ProjectPagedResponse> findAllPageable(ProjectsPagedRequest projectsPagedRequest) {
        Page<Project> pagedProjects = projectService.findAllProjectsPageable(
                PagedProjectsRequestDTO.builder()
                        .page(projectsPagedRequest.page)
                        .pageSize(projectsPagedRequest.pageSize)
                        .sortDirection(projectsPagedRequest.sortDirection)
                        .sortProperty(projectsPagedRequest.sortProperty)
                        .projectName(projectsPagedRequest.getProjectName())
                        .projectStatus(projectsPagedRequest.getProjectStatus())
                        .sourceLanguage(projectsPagedRequest.getSourceLanguage())
                        .from(projectsPagedRequest.getFrom())
                        .to(projectsPagedRequest.getTo())
                        .build());
        List<ProjectPagedResponse> projectsResponse = pagedProjects.get().map(project -> ProjectPagedResponse.builder().
                projectName(project.getProjectName())
                .projectStatus(project.getProjectStatus().name())
                .projectDescription(project.getDescription())
                .sourceLanguage(project.getSourceLanguage())
                .targetLanguages(project.getTargetLanguages().stream()
                        .map(TargetLanguage::getName)
                        .collect(Collectors.toList()))
                .dateCreated(project.getDateCreated()).build()).toList();
        return new PageImpl<>(projectsResponse, pagedProjects.getPageable(), pagedProjects.getTotalElements());
    }
}