package com.task.test.geniusee.repository;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MovieSpecification {

  public Specification<Movie> getSpecification(SearchCriteria searchCriteria) {
    if(searchCriteria.getValue() == null || searchCriteria.getKey() == null) {
      return null;
    }
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
  }
}

