package com.memsource.memsourceapp.service;

import com.memsource.memsourceapp.domain.Project;
import com.memsource.memsourceapp.domain.dto.PagedProjectsRequestDTO;
import com.memsource.memsourceapp.http_client.response.FetchedProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    List<Project> fetchLatestProjects(String authorizationHeader);
    Project saveProject(FetchedProjectResponse projectDTO);

    Page<Project> findAllProjectsPageable(PagedProjectsRequestDTO pagedProjectsRequestDTO);


}
