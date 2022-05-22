package com.memsource.memsourceapp.templates;

import org.springframework.data.domain.Page;

public interface PagedEntityControllerTemplate<FilterType extends PagedEntityFilterRequest, EntityPageResponse> {

    Page<EntityPageResponse> findAllPageable(FilterType filterType);
}
