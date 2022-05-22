package com.memsource.memsourceapp.templates;

import org.springframework.data.domain.Page;

public interface PagedEntityMapperTemplate<FilterType extends PagedEntityFilterRequest, EntityPageResponse> {

    Page<EntityPageResponse> findAllPageable(FilterType filterType);
}
