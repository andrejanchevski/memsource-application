package com.memsource.memsourceapp.mapper.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.response.ProjectResponse;
import com.memsource.memsourceapp.mapper.ProjectMapper;
import com.memsource.memsourceapp.service.ProjectService;
import com.memsource.memsourceapp.util.JsonWebTokenUtils;
import lombok.RequiredArgsConstructor;
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
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
}
