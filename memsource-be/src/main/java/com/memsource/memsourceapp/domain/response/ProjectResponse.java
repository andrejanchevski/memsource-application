package com.memsource.memsourceapp.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {

    private Long projectId;
    private String projectName;
    private Long externalId;
    private String projectStatus;

}
