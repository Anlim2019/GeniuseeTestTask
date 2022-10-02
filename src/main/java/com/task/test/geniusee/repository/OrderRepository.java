package com.task.test.geniusee.repository;

import com.task.test.geniusee.entity.Movie;
import com.task.test.geniusee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
    JpaSpecificationExecutor<Order> {
}
