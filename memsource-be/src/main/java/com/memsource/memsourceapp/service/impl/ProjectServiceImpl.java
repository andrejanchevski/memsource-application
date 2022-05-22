package com.memsource.memsourceapp.service.impl;

import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.TargetLanguage;
import com.memsource.memsourceapp.domain.dto.PagedProjectsRequestDTO;
import com.memsource.memsourceapp.enums.ProjectStatus;
import com.memsource.memsourceapp.http_client.ProjectsHolderClient;
import com.memsource.memsourceapp.http_client.response.FetchedProjectResponse;
import com.memsource.memsourceapp.http_client.response.FetchedProjectsPageResponse;
import com.memsource.memsourceapp.repository.ProjectRepository;
import com.memsource.memsourceapp.repository.TargetLanguageRepository;
import com.memsource.memsourceapp.service.ProjectService;
import com.memsource.memsourceapp.templates.QueryTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectsHolderClient projectsHolderClient;
    private final ProjectRepository projectRepository;
    private final TargetLanguageRepository targetLanguageRepository;

    private final QueryTemplate queryTemplate;

    @Override
    @Transactional
    public List<Project> fetchLatestProjects(String apiClientAuthenticationToken) {
        log.info(String.format("Starting to fetch projects from External API at %s", ZonedDateTime.now()));
        ResponseEntity<FetchedProjectsPageResponse> fetchedProjectsResponseEntity =
                projectsHolderClient.getCreatedProjects(String.format("ApiToken %s", apiClientAuthenticationToken));
        log.info(String.format("Objects fetched from External API at %s", ZonedDateTime.now()));
        return Objects.requireNonNull(fetchedProjectsResponseEntity.getBody()).getContent().stream()
                .filter(projectResponse -> projectRepository.findByExternalId(projectResponse.getId()).isEmpty())
                .map(this::saveProject)
                .collect(Collectors.toList());
    }

    @Override
    public Project saveProject(FetchedProjectResponse projectDTO) {
        log.info(String.format("Creating project with data %s", projectDTO));
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

    @Override
    public Page<Project> findAllProjectsPageable(PagedProjectsRequestDTO pagedProjectsRequestDTO) {
        log.info(String.format("Started fetching projects with filters %s", pagedProjectsRequestDTO));
        String sortProp = "dateCreated";
        Sort.Direction sortDirection = Sort.Direction.DESC;
        if(pagedProjectsRequestDTO.getSortDirection() != null){
            sortDirection = pagedProjectsRequestDTO.getSortDirection();
        }
        if(pagedProjectsRequestDTO.getSortProperty() != null){
            sortProp = pagedProjectsRequestDTO.getSortProperty();
        }
        Pageable pageable = PageRequest.of(pagedProjectsRequestDTO.getPage() - 1,
                pagedProjectsRequestDTO.getPageSize(), Sort.by(sortDirection, sortProp));
        Specification<Project> customSpecification =
                createSpecificationForProjects(pagedProjectsRequestDTO.getProjectName(),
                        pagedProjectsRequestDTO.getSourceLanguage(),
                        pagedProjectsRequestDTO.getProjectStatus(),
                        pagedProjectsRequestDTO.getFrom(),
                        pagedProjectsRequestDTO.getTo());

        return projectRepository.findAll(customSpecification, pageable);
    }

    private Specification<Project> createSpecificationForProjects(
            String projectName,
            String sourceLanguage,
            String projectStatus,
            ZonedDateTime from,
            ZonedDateTime to
    ){
        return queryTemplate.chainAndSpecifications(
                queryTemplate.like(List.of("projectName"), projectName),
                queryTemplate.equals("projectStatus", ProjectStatus.valueOf(projectStatus).ordinal()),
                queryTemplate.like(List.of("sourceLanguage"), sourceLanguage ),
                queryTemplate.between(List.of("dateCreated"), from, to)
        );
    }

}
