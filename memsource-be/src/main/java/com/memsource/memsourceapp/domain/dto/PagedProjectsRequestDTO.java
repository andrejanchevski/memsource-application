package com.memsource.memsourceapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedProjectsRequestDTO {

    private Integer pageSize;
    private Integer page;
    private String sortProperty;
    private Sort.Direction sortDirection;
    private String projectName;
    private String sourceLanguage;
    private String projectStatus;
    private ZonedDateTime from;
    private ZonedDateTime to;
}
