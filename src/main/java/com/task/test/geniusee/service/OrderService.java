package com.task.test.geniusee.service;

import static java.lang.String.format;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.dto.OrderDto;
import com.task.test.geniusee.entity.Order;
import com.task.test.geniusee.mapper.OrderMapper;
import com.task.test.geniusee.repository.OrderRepository;
import com.task.test.geniusee.repository.MovieSpecification;
import com.task.test.geniusee.repository.OrderSpecification;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final OrderSpecification orderSpecification;

  public OrderDto findById(final Long id) {
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no order with id %d", id)));
    return this.orderMapper.toDto(order);
  }

  public List<OrderDto> findAll(SearchCriteria searchCriteria, Integer page, Integer size) {
    Pageable paging = PageRequest.of(page, size);
    return this.orderRepository.findAll(orderSpecification.getSpecification(searchCriteria), paging)
        .stream()
        .map(this.orderMapper::toDto).collect(Collectors.toList());
  }

  public OrderDto create(final OrderDto orderDto) {
    return this.orderMapper.toDto(
        this.orderRepository.save(this.orderMapper.toEntity(orderDto)));
  }

  public OrderDto update(final OrderDto orderDto) {
    Long id = orderDto.getId();
    Order order = this.orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no order with id %d", id)));
    this.orderMapper.updateProperties(orderDto, order);
    return this.orderMapper.toDto(this.orderRepository.save(order));
  }

  public void removeById(final Long id) {
    this.orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no order with id %d", id)));
    this.orderRepository.deleteById(id);
  }
}
