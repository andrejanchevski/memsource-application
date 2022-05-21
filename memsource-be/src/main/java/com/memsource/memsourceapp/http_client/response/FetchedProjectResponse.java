package com.memsource.memsourceapp.http_client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchedProjectResponse {
    private Integer internalId;
    private ZonedDateTime dateCreated;
    private Long id;
    private String name;
    private String sourceLang;
    private List<String> targetLangs;
    private String status;
    private String note;

}
