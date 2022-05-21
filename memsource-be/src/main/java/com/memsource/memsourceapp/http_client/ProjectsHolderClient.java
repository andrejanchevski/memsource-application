package com.memsource.memsourceapp.http_client;

import com.memsource.memsourceapp.config.ProjectsHolderClientConfiguration;
import com.memsource.memsourceapp.http_client.request.AuthenticateUserRequest;
import com.memsource.memsourceapp.http_client.response.AuthenticatedUserLoginResponse;
import com.memsource.memsourceapp.http_client.response.FetchedProjectResponse;
import com.memsource.memsourceapp.http_client.response.FetchedProjectsPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "projectsclient",
        url = "${sample.client.url}",
        configuration = ProjectsHolderClientConfiguration.class)
public interface ProjectsHolderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthenticatedUserLoginResponse> authenticateUser(
            @RequestBody AuthenticateUserRequest authenticateUserRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FetchedProjectsPageResponse> getCreatedProjects(
            @RequestHeader(value = "Authorization") String authorizationHeader);


}
