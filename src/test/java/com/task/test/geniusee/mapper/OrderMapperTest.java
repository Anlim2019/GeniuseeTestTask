package com.task.test.geniusee.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.task.test.geniusee.dto.OrderDto;
import com.task.test.geniusee.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
@ComponentScan("com.task.test.geniusee.mapper")
public class OrderMapperTest {

  @Autowired
  private OrderMapper orderMapper;

  @Test
  void shouldBeMappedToDto() {
    Order expected = getOrder();
    OrderDto actual = orderMapper.toDto(expected);


    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getPrice(), actual.getPrice())
    );
  }

  @Test
  void shouldBeMappedToEntity() {
    OrderDto expected = getOrderDto();
    Order actual = orderMapper.toEntity(expected);


    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getPrice(), actual.getPrice())
    );
  }

  @Test
  void shouldUpdateProperties() {
    OrderDto source = getOrderDto();
    Order target = getOrder();

    orderMapper.updateProperties(source, target);
    assertAll(
        () -> assertEquals(target.getId(), source.getId()),
        () -> assertEquals(target.getPrice(), source.getPrice())
    );
  }



  private static Order getOrder() {
    Order order = new Order();
    order.setPrice(123.3);
    order.setId(1L);
    return order;
  }

  private static OrderDto getOrderDto() {
    OrderDto order = new OrderDto();
    order.setPrice(5678.6);
    order.setId(1L);
    return order;
  }
}
