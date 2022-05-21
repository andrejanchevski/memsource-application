package com.memsource.memsourceapp.http_client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchedProjectsPageResponse {
    private Integer pageNumber;
    private List<FetchedProjectsPageResponse> content = new ArrayList<>();
    private Integer numberOfElements;
    private Integer totalElements;
    private Integer pageSize;
    private Integer totalPages;
}
