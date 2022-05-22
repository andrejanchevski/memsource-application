package com.memsource.memsourceapp.templates;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;

@Component
public class QueryTemplate {

    public <T> Specification<T> equals(String propertyName, Object value) {

        if (value == null) {
            return Specification.where(null);
        }

        Specification<T> result =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(propertyName), value);
        return result.and(isNotNull(propertyName));
    }

    public <T> Specification<T> like(Collection<String> propertyNames, String value) {
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(generatePropertyPath(propertyNames, root)),
                "%" + value.toLowerCase() + "%");
    }

    public <T> Specification<T> between(Collection<String> propertyNames, ZonedDateTime from, ZonedDateTime to) {
        if (from == null && to == null) {
            return Specification.where(null);
        } else if (from == null) {
            return lessThanOrEquals(propertyNames, to);
        } else if (to == null) {
            return greaterThanOrEqual(propertyNames, from);
        }

        Specification<T> result =
                (root, query, criteriaBuilder) -> criteriaBuilder.between(generatePropertyPath(propertyNames, root), from,
                        to);
        return result;
    }

    @SafeVarargs
    final public <T> Specification<T> chainAndSpecifications(Specification<T>... specifications) {
        return Arrays.stream(specifications)
                .reduce(Specification::and)
                .orElseThrow(() -> new RuntimeException("The chain of specifications cannot be null"));
    }

    private <T, X> Path<X> generatePropertyPath(Collection<String> propertyNames, Root<T> root) {
        Path<X> actualPropertyPath = null;
        for (String propertyName : propertyNames)
            if (actualPropertyPath == null) {
                actualPropertyPath = root.get(propertyName);
            } else {
                actualPropertyPath = actualPropertyPath.get(propertyName);
            }

        return actualPropertyPath;
    }


    private  <T> Specification<T> isNotNull(String propertyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(propertyName));
    }

    private <T> Specification<T> lessThanOrEquals(Collection<String> propertyNames, ZonedDateTime value) {
        if (value == null) {
            return Specification.where(null);
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                generatePropertyPath(propertyNames, root), value);
    }

    private <T> Specification<T> greaterThanOrEqual(Collection<String> propertyNames, ZonedDateTime value) {
        if (value == null) {
            return Specification.where(null);
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                generatePropertyPath(propertyNames, root), value);
    }


}
