package com.memsource.memsourceapp.domain.request;

import com.memsource.memsourceapp.templates.PagedEntityFilterRequest;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.time.ZonedDateTime;


@Getter
@Setter
public class ProjectsPagedRequest extends PagedEntityFilterRequest {

    private String projectName;
    private String sourceLanguage;
    private String projectStatus;
    private ZonedDateTime from;
    private ZonedDateTime to;

    public ProjectsPagedRequest(Integer page, Integer pageSize, String sortProperty, Sort.Direction sortDirection) {
        super(page, pageSize, sortProperty, sortDirection);
    }
}
