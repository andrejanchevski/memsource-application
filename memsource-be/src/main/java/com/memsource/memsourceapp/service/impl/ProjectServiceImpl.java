package com.memsource.memsourceapp.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.TargetLanguage;
import com.memsource.memsourceapp.enums.ProjectStatus;
import com.memsource.memsourceapp.http_client.ProjectsHolderClient;
import com.memsource.memsourceapp.http_client.response.FetchedProjectResponse;
import com.memsource.memsourceapp.http_client.response.FetchedProjectsPageResponse;
import com.memsource.memsourceapp.repository.ProjectRepository;
import com.memsource.memsourceapp.repository.TargetLanguageRepository;
import com.memsource.memsourceapp.service.ProjectService;
import com.memsource.memsourceapp.util.JsonWebTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectsHolderClient projectsHolderClient;
    private final ProjectRepository projectRepository;
    private final JsonWebTokenUtils jsonWebTokenUtils;

    private final TargetLanguageRepository targetLanguageRepository;

    @Override
    @Transactional
    public List<Project> fetchLatestProjects(String authorizationHeader) {
        DecodedJWT decodedJWT = jsonWebTokenUtils.decodedJWT(authorizationHeader);
        String apiClientAuthenticationToken = decodedJWT.getClaim("memsourceApiToken").asString();
        ResponseEntity<FetchedProjectsPageResponse> fetchedProjectsResponseEntity =
                projectsHolderClient.getCreatedProjects(String.format("ApiToken %s", apiClientAuthenticationToken));
        return Objects.requireNonNull(fetchedProjectsResponseEntity.getBody()).getContent().stream()
                .filter(projectResponse -> projectRepository.findByExternalId(projectResponse.getId()).isEmpty())
                .map(this::saveProject)
                .collect(Collectors.toList());
    }

    @Override
    public Project saveProject(FetchedProjectResponse projectDTO) {
        List<TargetLanguage> targetLanguages = projectDTO.getTargetLangs()
                .stream()
                .filter(targetLang -> targetLanguageRepository.findByName(targetLang).isEmpty())
                .map(nonExistingTargetLang ->
                        targetLanguageRepository.save(TargetLanguage.builder().name(nonExistingTargetLang).build()))
                .collect(Collectors.toList());

        return projectRepository.save(Project.builder()
                        .externalId(projectDTO.getId())
                        .projectName(projectDTO.getName())
                        .sourceLanguage(projectDTO.getSourceLang())
                        .projectStatus(ProjectStatus.valueOf(projectDTO.getStatus()))
                        .targetLanguages(targetLanguages)
                        .description(projectDTO.getNote())
                        .dateCreated(projectDTO.getDateCreated())
                .build());
    }




}
