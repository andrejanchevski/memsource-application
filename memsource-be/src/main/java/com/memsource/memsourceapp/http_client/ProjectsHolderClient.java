package com.memsource.memsourceapp.http_client;

import com.memsource.memsourceapp.config.ProjectsHolderClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "projectsclient",
        url = "${sample.client.url}",
        configuration = ProjectsHolderClientConfiguration.class)
public interface ProjectsHolderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> authenticateUser();

    @RequestMapping(method = RequestMethod.GET, value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getCreatedProjects(
            @RequestHeader(value = "Authorization") String authorizationHeader);


}
