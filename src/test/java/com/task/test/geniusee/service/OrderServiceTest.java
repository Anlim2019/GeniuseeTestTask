package com.task.test.geniusee.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.dto.OrderDto;
import com.task.test.geniusee.entity.Order;
import com.task.test.geniusee.mapper.OrderMapper;
import com.task.test.geniusee.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@ComponentScan("com.task.test.geniusee.mapper")
public class OrderServiceTest {


  private static final Long ORDER_ID = 1L;
  private static final OrderDto ORDER_DTO = getOrderDto();
  private static final Order ORDER = getOrder();
  @InjectMocks
  private OrderService orderService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private OrderMapper orderMapper;


  @Test
  void shouldReturnOrderById() {
    when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));
    when(orderMapper.toDto(ORDER)).thenReturn(ORDER_DTO);

    OrderDto result = orderService.findById(ORDER_ID);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(ORDER_DTO, result)
    );
  }

  @Test
  void shouldReturnListOfOrders() {
    Pageable pageable = PageRequest.of(0, 2);
    List<Order> orders = new ArrayList<>(List.of(ORDER, ORDER));
    Page<Order> page = new PageImpl<>(orders);
    when(orderRepository.findAll(pageable))
        .thenReturn(page);
    List<OrderDto> result = orderService.findAll(new SearchCriteria(),0, 2);

    assertEquals(result.size(), orders.size());
  }

  @Test
  void givenMovie_ShouldBeSaved() {
    when(orderMapper.toEntity(ORDER_DTO)).thenReturn(ORDER);
    when(orderMapper.toDto(ORDER)).thenReturn(ORDER_DTO);
    when(orderRepository.save(ORDER)).thenReturn(ORDER);

    OrderDto result = orderService.create(ORDER_DTO);

    verify(orderMapper).toDto(ORDER);
    verify(orderRepository).save(ORDER);
    verify(orderMapper).toEntity(ORDER_DTO);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(ORDER_DTO, result)
    );
  }

  @Test
  void shouldUpdate() {
    when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));
    when(orderRepository.save(ORDER)).thenReturn(ORDER);
    when(orderMapper.toDto(ORDER)).thenReturn(ORDER_DTO);

    OrderDto result = orderService.update(ORDER_DTO);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(result, ORDER_DTO)
    );
  }

  @Test
  void shouldDelete() {
    when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));

    orderService.removeById(ORDER_ID);

    verify(orderRepository).deleteById(ORDER_ID);
  }


  private static Order getOrder() {
    Order order = new Order();
    order.setPrice(123.3);
    order.setId(1L);

    return order;
  }

  private static OrderDto getOrderDto() {
    OrderDto order = new OrderDto();
    order.setPrice(123.3);
    order.setId(1L);


    return order;
  }
}
