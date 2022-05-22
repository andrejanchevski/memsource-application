package com.memsource.memsourceapp.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPagedResponse {
    private String projectName;
    private String projectStatus;
    private String sourceLanguage;
    private List<String> targetLanguages;
    private String projectDescription;
    private ZonedDateTime dateCreated;
}
