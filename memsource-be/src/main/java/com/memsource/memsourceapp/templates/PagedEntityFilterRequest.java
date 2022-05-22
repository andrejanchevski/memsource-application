package com.memsource.memsourceapp.templates;

import lombok.*;
import org.springframework.data.domain.Sort;


@Data
@AllArgsConstructor
public class PagedEntityFilterRequest {
    public final Integer page;
    public final Integer pageSize;
    public final String sortProperty;
    public final Sort.Direction sortDirection;
}
